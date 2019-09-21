package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URLConnection;
import java.util.List;

public class WikiConnectionTest {
    @Test
    public void testIsOnline() {
        WikiConnection wikiConnection = new WikiConnection();
        Assert.assertTrue(wikiConnection.isOnline());
    }

    @Test
    public void testConnectToWikipedia() {
        WikiConnection wikiConnection = new WikiConnection();
        Assert.assertNotNull(wikiConnection.connectToWikipedia("youtube"));
    }

    @Test
    public void testSearchWikipediaHasResults() throws Exception{
        RevisionParser parser = new RevisionParser();
        String searchTerm = "Zappa";
        String encodedSearch = WikiURLEncoder.encode(searchTerm);
        URLConnection connection = WikiConnection.connectToWikipedia(encodedSearch);
        InputStream inputStream = connection.getInputStream();
        List<Revision> revisionList = parser.parse(inputStream);
        Assert.assertEquals(revisionList.size(), 24);
    }

    @Test
    public void testIfNoResults() {
        WikiConnection wikiConnection = new WikiConnection();
        Assert.assertFalse(wikiConnection.areResultsFor("asdklfj"));
    }

    @Test
    public void testIfHasResults() {
        WikiConnection wikiConnection = new WikiConnection();
        Assert.assertTrue(wikiConnection.areResultsFor("Zappa"));
    }
}
