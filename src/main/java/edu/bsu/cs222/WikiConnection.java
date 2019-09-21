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
}
