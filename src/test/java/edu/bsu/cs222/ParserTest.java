package edu.bsu.cs222;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class ParserTest {
    @Test
    public void testParsable() throws IOException, SAXException {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertNotNull(revisions);
    }
    @Test
    public void testParseGetUser() throws IOException, SAXException {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertEquals("RL0919", revisions.get(0).getUser());
    }

    @Test
    public void testParseGetTimestamp() throws IOException, SAXException {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertEquals("2019-09-14T21:14:54Z", revisions.get(0).getTimestamp());
    }

    @Test
    public void testParseIsNotAnonymous() throws  IOException, SAXException {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertEquals(false, revisions.get(0).isAnonymous());
    }

    @Test
    public void testParseIsAnonymous() throws IOException, SAXException {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertEquals(true, revisions.get(3).isAnonymous());
    }
}
