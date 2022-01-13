package de.hfu;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void istErstesHalbjahr() {
        assertEquals(false, Util.istErstesHalbjahr(7));
        assertEquals(true, Util.istErstesHalbjahr(6));
        assertEquals(true, Util.istErstesHalbjahr(1));
        assertEquals(false, Util.istErstesHalbjahr(12));
    }

    @Test(expected = IllegalArgumentException.class)
    public void zeroThrowsIllegalArgumentException() {
        Util.istErstesHalbjahr(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void thirteenThrowsIllegalArgumentException() {
        Util.istErstesHalbjahr(13);
    }
}