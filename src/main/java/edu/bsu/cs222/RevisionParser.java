package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {
    public List<Revision> parse(InputStream sampleInput) throws IOException, SAXException {
        JsonParser parser = new JsonParser();
        Reader reader = new InputStreamReader(sampleInput);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
        JsonArray revisionArray = null;

        for (Map.Entry<String,JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            revisionArray = entryObject.getAsJsonArray("revisions");
        }

        List<Revision> result = new ArrayList<>();
        for (int i = 0; i < revisionArray.size(); i++){
            result.add(new Revision());
        }

        for (int i = 0; i < revisionArray.size(); i++){
            System.out.println(revisionArray.get(i));
        }
        return result;
    }

}
