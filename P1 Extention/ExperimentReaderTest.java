package com.company;

import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class ExperimentReaderTest {


    @org.junit.Test
    public void testIntToTime() throws Exception {
        assertEquals("null01:00:00",ExperimentReader.intToTime(3600) );

    }

    @org.junit.Test
    public void testTimeToInt() throws Exception {
        assertEquals(ExperimentReader.timeToInt("01:00:00"),3600);
        assertNotEquals(ExperimentReader.timeToInt("01:00:00"),0);

    }
}