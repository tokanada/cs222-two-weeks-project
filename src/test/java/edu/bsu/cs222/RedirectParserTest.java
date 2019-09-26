package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

public class RedirectParserTest {
    @Test
    public void testRedirectParser() {
        RedirectParser parser = new RedirectParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        Redirect redirect = parser.parse(inputStream);
        Assert.assertNotNull(redirect);
    }

    @Test
    public void testRetrieveOriginalTerm() {
        RedirectParser parser = new RedirectParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        Redirect redirect = parser.parse(inputStream);
        Assert.assertEquals("Zappa", redirect.getOriginalSearchTerm());
    }

    @Test
    public void testRetrieveRedirectTerm() {
        RedirectParser parser = new RedirectParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        Redirect redirect = parser.parse(inputStream);
        Assert.assertEquals("Frank Zappa", redirect.getRedirectSearchTerm());
    }

    @Test
    public void testRetrieveRedirectTermFromApi() throws IOException {
        String searchTerm = "zappa";
        String encodedSearch = WikiURLEncoder.encode(searchTerm);
        URLConnection urlConnection = WikiConnection.connectToWikipedia(encodedSearch);
        InputStream inputStream = urlConnection.getInputStream();
        RedirectParser parser = new RedirectParser();
        Redirect redirect = parser.parse(inputStream);
        Assert.assertEquals("Frank Zappa", redirect.getRedirectSearchTerm());
    }

}
