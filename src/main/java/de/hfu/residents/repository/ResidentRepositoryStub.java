package de.hfu.residents.repository;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.hfu.residents.domain.Resident;

/**
 * @author Hermann Lau
 */

public class ResidentRepositoryStub implements ResidentRepository {

    List<Resident> residents;

    public ResidentRepositoryStub(List<Resident> residents) {
        this.residents = residents;
    }

    @Override
    public List<Resident> getResidents() {
        return residents;
    }
}