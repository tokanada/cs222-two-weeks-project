package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class TimeLocalizerTest {

    @Test
    public void testGetLocalizedDate24Hour() {
        String localizedDate = TimeLocalizer.getLocalizedDateTime("2019-09-14T13:10:20Z");
        Assert.assertEquals("1:10:20 PM | 09/14/2019", localizedDate);
    }

    @Test
    public void testGetLocalizeDate() {
        String localizedDate = TimeLocalizer.getLocalizedDateTime("2019-09-14T07:10:20Z");
        Assert.assertEquals("7:10:20 AM | 09/14/2019", localizedDate);
    }
}
