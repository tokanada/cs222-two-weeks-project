package edu.bsu.cs222;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeLocalizer {
    public LocalDateTime convertToDate(String dateString) {
        try {
            dateString = dateString.replaceAll("T", "");
            dateString = dateString.replaceAll("Z", "");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
            LocalDateTime date = LocalDateTime.parse(dateString, formatter);
            return date;
        } catch (Exception e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public LocalDateTime getMostRecentDate(LocalDateTime firstDate, LocalDateTime secondDate) {
        LocalDateTime mostRecentDate;
        if(firstDate.isAfter(secondDate)){
            mostRecentDate = firstDate;
        }else {
            mostRecentDate = secondDate;
        }
        return mostRecentDate;
    }
}
