package de.hfu.residents.service;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.repository.ResidentRepositoryStub;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.easymock.EasyMock.*;

public class ResidentServiceTest {

    private final Resident lurayJenkins = new Resident(
            "Luray",
            "Jenkins",
            "TheHoodlum Str.",
            "Hoodies",
            new GregorianCalendar(1891, Calendar.DECEMBER, 5).getTime()
    );
    private final Resident pensonMcGreary = new Resident(
            "Penson",
            "McGreary",
            "Prewlaner Str.",
            "Tokyo",
            new GregorianCalendar(1885, Calendar.JULY, 27).getTime()
    );
    private final Resident plierosLerinodos = new Resident(
            "Plieros",
            "Lerinodos",
            "Peninusula Str.",
            "Guatemala",
            new GregorianCalendar(2005, Calendar.AUGUST, 14).getTime()
    );

    @Test
    public void getUniqueResidentReturnsResident() throws ResidentServiceException {
        List<Resident> residents = new ArrayList<>();
        residents.add(lurayJenkins);
        residents.add(pensonMcGreary);

        ResidentRepository repo = new ResidentRepositoryStub(residents);
        ResidentService service = new BaseResidentService(repo);

        Resident actual = service.getUniqueResident(lurayJenkins);
        assertEquals(lurayJenkins, actual);
    }

    @Test(expected = ResidentServiceException.class)
    public void getUniqueResidentThrowsOnMultipleMatches() throws ResidentServiceException {
        List<Resident> residents = new ArrayList<>();
        residents.add(lurayJenkins);
        residents.add(lurayJenkins);
        residents.add(pensonMcGreary);

        ResidentRepository repo = new ResidentRepositoryStub(residents);
        ResidentService service = new BaseResidentService(repo);

        // Throws
        service.getUniqueResident(lurayJenkins);
    }

    @Test(expected = ResidentServiceException.class)
    public void getUniqueResidentTrowsOnWildcard() throws ResidentServiceException {
        List<Resident> residents = new ArrayList<>();
        residents.add(lurayJenkins);

        ResidentRepository repo = new ResidentRepositoryStub(residents);
        ResidentService service = new BaseResidentService(repo);

        Resident filterResident = new Resident(
                "*",
                "*",
                "*",
                "*",
                null
        );

        // Throws
        service.getUniqueResident(filterResident);
    }

    @Test
    public void getFilteredResidentsListReturnsAllResidentsOnWildcard() {
        List<Resident> residents = new ArrayList<>();
        residents.add(lurayJenkins);
        residents.add(pensonMcGreary);

        ResidentRepository repo = new ResidentRepositoryStub(residents);
        ResidentService service = new BaseResidentService(repo);

        Resident filterResident = new Resident(
                "*",
                "*",
                "*",
                "*",
                null
        );

        List<Resident> actual = service.getFilteredResidentsList(filterResident);
        assertEquals(residents, actual);
    }

    @Test
    public void getFilteredResidentsListReturnsAllMatchingResidents() {
        List<Resident> residents = new ArrayList<>();
        residents.add(lurayJenkins);
        residents.add(pensonMcGreary);

        ResidentRepository repo = new ResidentRepositoryStub(residents);
        ResidentService service = new BaseResidentService(repo);

        Resident filterResident = new Resident(
                "*",
                "Jenkins",
                "*",
                "*",
                null
        );

        List<Resident> actual = service.getFilteredResidentsList(filterResident);
        assertEquals(residents, actual);
    }

    @Test
    public void getFilteredResidentsListReturnsAllMatchingResidentsOnBirthdate() {
        List<Resident> residents = new ArrayList<>();

        Date date = new GregorianCalendar(1885, Calendar.AUGUST, 27).getTime();
        lurayJenkins.setDateOfBirth(date);
        plierosLerinodos.setDateOfBirth(date);

        residents.add(lurayJenkins);
        residents.add(pensonMcGreary);
        residents.add(plierosLerinodos);

        ResidentRepository repo = new ResidentRepositoryStub(residents);
        ResidentService service = new BaseResidentService(repo);

        Resident filterResident = new Resident(
                "*",
                "*",
                "*",
                "*",
                lurayJenkins.getDateOfBirth()
        );

        residents.remove(pensonMcGreary);
        List<Resident> actual = service.getFilteredResidentsList(filterResident);
        assertEquals(residents, actual);
    }

    @Test
    public void getUniqueResidentReturnsResidentWithRepositoryMock() throws ResidentServiceException {
        List<Resident> residents = new ArrayList<>();
        residents.add(lurayJenkins);
        residents.add(pensonMcGreary);

        ResidentRepository repoMock = createMock(ResidentRepository.class);
        expect(repoMock.getResidents()).andReturn(residents);

        replay(repoMock);
        ResidentService service = new BaseResidentService(repoMock);
        Resident actual = service.getUniqueResident(lurayJenkins);
        verify(repoMock);

        assertThat(actual, equalTo(lurayJenkins));
    }
}
