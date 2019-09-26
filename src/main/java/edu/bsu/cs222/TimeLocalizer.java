package edu.bsu.cs222;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class TimeLocalizer {

    static String getLocalizedDateTime(String dateString) {
        LocalDateTime dateTime = convertToDateTime(dateString);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm:ss a '|' MM/dd/yyyy", Locale.US);
        return outputFormatter.format(dateTime);
    }

    private static LocalDateTime convertToDateTime(String dateString) {
        try {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            return LocalDateTime.parse(dateString, inputFormatter);
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }

}
