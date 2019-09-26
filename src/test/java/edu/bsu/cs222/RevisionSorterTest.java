package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

public class RevisionSorterTest {
    @Test
    public void testRevisionSort() {
        RevisionSorter sorter = new RevisionSorter();
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> unsortedList = parser.parse(inputStream);
        List<Revision> sortedList = sorter.sortByMostRevisions(unsortedList);
        Assert.assertNotEquals(sortedList, unsortedList);
    }

    @Test
    public void testRevisionGroupSort() {
        RevisionSorter sorter = new RevisionSorter();
        RevisionParser parser = new RevisionParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        List<Revision> unsortedList = parser.parse(inputStream);
        List<Revision> sortedList = sorter.sortByMostRevisions(unsortedList);
        inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
        unsortedList = parser.parse(inputStream);
        List<Revision> groupedList = sorter.groupByIP(unsortedList);
        Assert.assertNotEquals(groupedList, sortedList);
    }

    @Test
    public void testRevisionSortPullFromWikipedia() {
        try {
            RevisionSorter sorter = new RevisionSorter();
            RevisionParser parser = new RevisionParser();
            String encodedSearch = WikiURLEncoder.encode("zappa");
            URLConnection urlConnection = WikiConnection.connectToWikipedia(encodedSearch);
            InputStream inputStream = urlConnection.getInputStream();
            List<Revision> unsortedList = parser.parse(inputStream);
            List<Revision> sortedList = sorter.sortByMostRevisions(unsortedList);
            Assert.assertNotEquals(sortedList, unsortedList);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    @Test
    public void testRevisionGroupPullFromWikipedia() {
        try {
            RevisionSorter sorter = new RevisionSorter();
            RevisionParser parser = new RevisionParser();

            String encodedSearch = WikiURLEncoder.encode("zappa");
            URLConnection urlConnection = WikiConnection.connectToWikipedia(encodedSearch);

            InputStream inputStream = urlConnection.getInputStream();
            List<Revision> unsortedList = parser.parse(inputStream);
            List<Revision> sortedList = sorter.sortByMostRevisions(unsortedList);

            inputStream = getClass().getClassLoader().getResourceAsStream("WikipediaApiResults.json");
            unsortedList = parser.parse(inputStream);            List<Revision> groupedList = sorter.groupByIP(unsortedList);
            Assert.assertNotEquals(sortedList, groupedList);
        } catch (IOException e) {
            throw new RuntimeException(e.getCause());
        }
    }
}
