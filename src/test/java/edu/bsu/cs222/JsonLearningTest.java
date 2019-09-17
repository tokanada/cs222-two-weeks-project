package edu.bsu.cs222;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonLearningTest {

    @Test
    public void testCountRevisions (){
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        Assert.assertNotNull(rootElement);
    }
}
