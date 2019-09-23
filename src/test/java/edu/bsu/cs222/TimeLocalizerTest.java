package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TimeLocalizerTest {
    @Test
    public void testStringToDateConversion() {
        TimeLocalizer timeLocalizer = new TimeLocalizer();
        LocalDateTime date = timeLocalizer.convertToDate("2019-09-14T08:10:20Z");
        System.out.println(date);
        Assert.assertNotNull(date);
    }

    @Test
    public void testGetMostRecentDateCompare() {
        TimeLocalizer timeLocalizer = new TimeLocalizer();
        LocalDateTime firstDate = timeLocalizer.convertToDate("2019-09-14T08:10:20Z");
        LocalDateTime secondDate = timeLocalizer.convertToDate("2019-09-13T08:10:20Z");
        LocalDateTime mostRecentDate = timeLocalizer.getMostRecentDate(firstDate, secondDate);
        Assert.assertEquals(firstDate, mostRecentDate);
    }

    @Test
    public void testGetMostRecentTimeCompare() {
        TimeLocalizer timeLocalizer = new TimeLocalizer();
        LocalDateTime firstDate = timeLocalizer.convertToDate("2019-09-14T08:10:20Z");
        LocalDateTime secondDate = timeLocalizer.convertToDate("2019-09-14T08:11:20Z");
        LocalDateTime mostRecentDate = timeLocalizer.getMostRecentDate(firstDate, secondDate);
        Assert.assertEquals(secondDate, mostRecentDate);
    }
}
