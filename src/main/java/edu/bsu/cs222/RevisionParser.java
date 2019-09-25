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
    private boolean isRedirected;
    private JsonArray redirectArray = null;

    public List<Revision> parse(InputStream sampleInput) {
        try {
            List<Revision> revisionList = new ArrayList<>();
            JsonParser parser = new JsonParser();

            Reader reader = new InputStreamReader(sampleInput);

            JsonElement rootElement = parser.parse(reader);
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonObject pages = rootObject.getAsJsonObject("query").getAsJsonObject("pages");
            JsonObject redirects = rootObject.getAsJsonObject("query").getAsJsonObject("redirects");
            JsonArray revisionArray = null;
            isRedirected = checkForRedirect(redirects);
            revisionArray = populateJsonArray(revisionArray, pages);
            revisionList = jsonArrayReader(revisionList, revisionArray);
            return revisionList;
        } catch (Exception e){
            return null;
        }
    }

    public boolean isRedirected() {
        return isRedirected;
    }

    private void redirectReader(JsonObject redirects) {
        redirectArray = populateRedirectArray(redirectArray, redirects);
    }

    private JsonArray populateRedirectArray(JsonArray redirectArray, JsonObject redirects) {
        for (Map.Entry<String,JsonElement> entry : redirects.entrySet()) {
            JsonObject entryObject = entry.getValue().getAsJsonObject();
            redirectArray = entryObject.getAsJsonArray("revisions");
        }

        return redirectArray;
    }

    public String getRedirectInfo() {

    }

    private boolean checkForRedirect(JsonObject redirects) {
        if(redirects == null){
            return false;
        }else {
            redirectReader(redirects);
            return true;
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
