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
    public void testParse() throws IOException, SAXException {
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> revisions = parser.parse(inputStream);
        Assert.assertEquals("RL0919", revisions.get(1));
    }
}
