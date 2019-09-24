package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class RevisionSorterTest {
    @Test
    public void testRevisionSort() {
        RevisionSorter sorter = new RevisionSorter();
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> unsortedList = parser.parse(inputStream);
        List<Revision> sortedList = sorter.sortMostRevisions(unsortedList);

        String outputString = "";
        for (Revision revision : sortedList){
            outputString += "User: " + revision.getUser() + "\nDate: " + revision.getTimestamp() + "\n\n";
        }

        System.out.println(outputString);

        Assert.assertNotEquals(sortedList, unsortedList);
    }

    @Test
    public void testRevisionSortPullFromWikipedia() {
        RevisionSorter sorter = new RevisionSorter();
        List<Revision> unsortedList = new ArrayList<>();
        try {
            RevisionParser parser = new RevisionParser();
            String encodedSearch = WikiURLEncoder.encode("Zappa");
            URLConnection urlConnection = WikiConnection.connectToWikipedia(encodedSearch);
            InputStream inputStream = urlConnection.getInputStream();
            unsortedList = parser.parse(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
        List<Revision> sortedList = sorter.sortMostRevisions(unsortedList);

        String outputString = "";
        for (Revision revision : sortedList){
            outputString += "User: " + revision.getUser() + "\nDate: " + revision.getTimestamp() + "\n\n";
        }

        System.out.println(outputString);

        Assert.assertNotEquals(sortedList, unsortedList);
    }
}
