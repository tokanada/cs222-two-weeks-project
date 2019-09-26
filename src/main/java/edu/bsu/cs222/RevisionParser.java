package edu.bsu.cs222;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RevisionParser {

    public List<Revision> parse(InputStream inputStream) {
        try {
            JsonParser parser = new JsonParser();
            Reader reader = new InputStreamReader(inputStream);
            JsonElement rootElement = parser.parse(reader);
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
            JsonArray revisionArray = new JsonArray();
            revisionArray = populateJsonArray(revisionArray, pages);
            return jsonArrayReader(revisionArray);
        } catch (Exception e){
            return null;
        }
    }

    private JsonArray populateJsonArray(JsonArray jsonArray, JsonObject pagesObject){
        for (Map.Entry<String,JsonElement> entry : pagesObject.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            jsonArray = entryObject.getAsJsonArray("revisions");
        }
        return jsonArray;
    }

    private List<Revision> jsonArrayReader(JsonArray jsonArray) {
        List<Revision> revisionList = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            String userElement = jsonElement.getAsJsonObject().get("user").getAsString();
            String timestampElement = jsonElement.getAsJsonObject().get("timestamp").getAsString();
            timestampElement = TimeLocalizer.getLocalizedDateTime(timestampElement);
            boolean isAnonymous = jsonElement.getAsJsonObject().has("anon");
            Revision revision = new Revision(userElement, timestampElement, isAnonymous);
            revisionList.add(revision);
        }
        return revisionList;
    }
}
