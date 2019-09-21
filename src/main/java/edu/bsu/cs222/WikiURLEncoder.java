package edu.bsu.cs222;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikiURLEncoder {
    public static String encode(String inputString) {
        try{
            String encoding = URLEncoder.encode(inputString, StandardCharsets.UTF_8.toString());
            return encoding;
        } catch (UnsupportedEncodingException e){
            throw new RuntimeException(e.getCause());
        }
    }
}
