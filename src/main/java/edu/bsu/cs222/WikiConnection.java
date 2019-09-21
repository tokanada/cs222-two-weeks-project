package edu.bsu.cs222;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WikiConnection {
    public boolean isOnline() {
        try {
            URL url = new URL("https://www.wikipedia.org/");
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            return true;
        } catch (IOException e){
            return false;
        }
    }

    public static URLConnection connectToWikipedia(String searchTerm) {
        try {
            URL url = new URL("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles="+ searchTerm +"&redirects=1&rvprop=timestamp%7Cuser&rvlimit=24");
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "RevisionTracker/0.1 (dtao@bsu.edu)");
            urlConnection.connect();
            return urlConnection;
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
