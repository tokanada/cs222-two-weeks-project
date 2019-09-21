package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class WikiConnectionTest {
    @Test
    public void testIsOnline() {
        WikiConnection wikiConnection = new WikiConnection();
        Assert.assertTrue(wikiConnection.isOnline());
    }
}
