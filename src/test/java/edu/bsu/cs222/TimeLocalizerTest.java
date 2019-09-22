package edu.bsu.cs222;

import org.junit.Test;

import java.util.Date;

public class TimeLocalizerTest {
    @Test
    public void testStringToDateConversion() {
        TimeLocalizer timeLocalizer = new TimeLocalizer();
        Date date = timeLocalizer.convertToDate("2019-09-14T08:14:03Z");

    }
}
