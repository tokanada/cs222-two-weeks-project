package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class WikiRevisionRequesterTest {
    @Test
    public void testRetrieveResultsByGrouping() {
        String searchTerm = "Zappa";
        WikiRevisionRequester revisionRequester = new WikiRevisionRequester();
        String resultsByGrouping = revisionRequester.retrieveResultsByGrouping(searchTerm);
        Assert.assertNotNull(resultsByGrouping);
    }

    @Test
    public void testRetrieveResultsByFrequency() {
        String searchTerm = "Zappa";
        WikiRevisionRequester revisionRequester = new WikiRevisionRequester();
        String resultsByFrequency = revisionRequester.retrieveResultsByFrequency(searchTerm);
        Assert.assertNotNull(resultsByFrequency);
    }

    @Test
    public void testRetreiveResultsByTime() {
        String searchTerm = "Zappa";
        WikiRevisionRequester revisionRequester = new WikiRevisionRequester();
        String resultsByTime = revisionRequester.retrieveResultsByTime(searchTerm);
        Assert.assertNotNull(resultsByTime);
    }

    @Test
    public void testVerifyDifferentResults() {
        String searchTerm = "Zappa";
        WikiRevisionRequester revisionRequester = new WikiRevisionRequester();
        String resultsByFrequency = revisionRequester.retrieveResultsByFrequency(searchTerm);
        String resultsByGrouping = revisionRequester.retrieveResultsByGrouping(searchTerm);
        String resultsByTime = revisionRequester.retrieveResultsByTime(searchTerm);
        Assert.assertNotEquals(resultsByFrequency, resultsByGrouping, resultsByTime);
    }
}
