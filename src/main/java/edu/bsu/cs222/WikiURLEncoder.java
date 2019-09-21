package edu.bsu.cs222;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikiURLEncoder {
    public static String encode(String inputString) {
        try{
            return URLEncoder.encode(inputString, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e){
            throw new RuntimeException(e.getCause());
        }
    }
}
