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

    public List<Revision> parse(InputStream sampleInput) {
        try {
            List<Revision> revisionList = new ArrayList<>();
            JsonParser parser = new JsonParser();

            Reader reader = new InputStreamReader(sampleInput);

            JsonElement rootElement = parser.parse(reader);
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
            JsonArray revisionArray = null;

            revisionArray = populateJsonArray(revisionArray, pages);

            revisionList = jsonArrayReader(revisionList, revisionArray);

            return revisionList;
        } catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }

    private List<Revision> jsonArrayReader(List<Revision> revisionList, JsonArray jsonArray) {
        for (JsonElement jsonElement : jsonArray) {
            String userElement = jsonElement.getAsJsonObject().get("user").getAsString();
            String timestampElement = jsonElement.getAsJsonObject().get("timestamp").getAsString();
            Boolean anonymous = jsonElement.getAsJsonObject().has("anon");
            Revision revision = new Revision(userElement, timestampElement, anonymous);
            revisionList.add(revision);
        }
        return revisionList;
    }

    private JsonArray populateJsonArray(JsonArray jsonArray, JsonObject pages){
        for (Map.Entry<String,JsonElement> entry : pages.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            jsonArray = entryObject.getAsJsonArray("revisions");
        }

        return jsonArray;
    }

}
