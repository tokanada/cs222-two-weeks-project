package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class RevisionParserTest {
    @Test
    public void testParsable() {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertNotNull(revisions);
    }
    @Test
    public void testParseGetUser() {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertEquals("RL0919", revisions.get(0).getUser());
    }

    @Test
    public void testParseGetTimestamp() {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertEquals("9:14:54 PM | 09/14/2019", revisions.get(0).getTimestamp());
    }

    @Test
    public void testParseIsNotAnonymous() {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertFalse(revisions.get(0).isAnonymous());
    }

    @Test
    public void testParseIsAnonymous() {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertTrue(revisions.get(3).isAnonymous());
    }
}
