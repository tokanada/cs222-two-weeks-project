package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

public class WikiConnectionTest {
    @Test
    public void testIsOnline() {
        Assert.assertTrue(WikiConnection.isOnline());
    }

    @Test
    public void testConnectToWikipedia() {
        Assert.assertNotNull(WikiConnection.connectToWikipedia("youtube"));
    }

    @Test
    public void testSearchWikipediaHasResults() throws Exception{
        RevisionParser parser = new RevisionParser();
        String searchTerm = "Zappa";
        String encodedSearch = WikiURLEncoder.encode(searchTerm);
        URLConnection urlConnection = WikiConnection.connectToWikipedia(encodedSearch);
        InputStream inputStream = urlConnection.getInputStream();
        List<Revision> revisionList = parser.parse(inputStream);
        Assert.assertEquals(revisionList.size(), 24);
    }

    @Test
    public void testIfUserIsReturned() throws IOException {
        RevisionParser parser = new RevisionParser();
        String searchTerm = "Zappa";
        String encodedSearch = WikiURLEncoder.encode(searchTerm);
        URLConnection urlConnection = WikiConnection.connectToWikipedia(encodedSearch);
        InputStream inputStream = urlConnection.getInputStream();
        List<Revision> revisionsList = parser.parse(inputStream);
        Assert.assertEquals("RL0919", revisionsList.get(0).getUser());
    }

    @Test
    public void testIfNoResults() {
        Assert.assertFalse(WikiConnection.hasWikiResults("asdklfj"));
    }

    @Test
    public void testIfHasResults() {
        Assert.assertTrue(WikiConnection.hasWikiResults("Zappa"));
    }
}
