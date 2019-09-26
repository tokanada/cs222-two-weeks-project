package edu.bsu.cs222;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class RedirectParser {

    public Redirect parse(InputStream inputStream) {
        try {
            JsonParser parser = new JsonParser();
            Reader reader = new InputStreamReader(inputStream);
            JsonElement rootElement = parser.parse(reader);
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonObject query = rootObject.getAsJsonObject("query").getAsJsonObject();
            JsonElement redirectElement = query.getAsJsonArray("redirects").get(0);
            if (redirectElement == null) {
                return null;
            }
            String originalSearchTerm = redirectElement.getAsJsonObject().get("from").getAsString();
            String newSearchTerm = redirectElement.getAsJsonObject().get("to").getAsString();
            return new Redirect(originalSearchTerm, newSearchTerm);
        } catch (Exception e) {
            return null;
        }
    }
}
