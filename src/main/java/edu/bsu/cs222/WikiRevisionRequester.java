package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.List;

class WikiRevisionRequester {

    String retrieveResultsByGrouping(String inputString) {
        if(isConnectedToInternet()){
            return "Could Not Connect to Wikipedia.\\nPlease check your connection and try again.";
        }
        InputStream inputStream = retrieveInputStream(inputString);
        List<Revision> revisionList = retrieveRevisionList(inputStream);
        List<Revision> groupedRevisionList = groupRevisionList(revisionList);
        inputStream = retrieveInputStream(inputString);
        String redirectMessage = checkForRedirects(inputStream);
        return printList(groupedRevisionList, redirectMessage);
    }

    String retrieveResultsByFrequency(String inputString) {
        if(isConnectedToInternet()){
            return "Could Not Connect to Wikipedia.\\nPlease check your connection and try again.";
        }
        InputStream inputStream = retrieveInputStream(inputString);
        List<Revision> revisionList = retrieveRevisionList(inputStream);
        List<Revision> sortedRevisionList = sortRevisionList(revisionList);
        inputStream = retrieveInputStream(inputString);
        String redirectMessage = checkForRedirects(inputStream);
        return printList(sortedRevisionList, redirectMessage);
    }

    String retrieveResultsByTime(String inputString) {
        if(isConnectedToInternet()){
            return "Could Not Connect to Wikipedia.\\nPlease check your connection and try again.";
        }
        InputStream inputStream = retrieveInputStream(inputString);
        List<Revision> revisionList = retrieveRevisionList(inputStream);
        inputStream = retrieveInputStream(inputString);
        String redirectMessage = checkForRedirects(inputStream);
        return printList(revisionList, redirectMessage);
    }

    private InputStream retrieveInputStream(String inputString){
        try {
            String encodedSearch = WikiURLEncoder.encode(inputString);
            URLConnection urlConnection = WikiConnection.connectToWikipedia(encodedSearch);
            return urlConnection.getInputStream();
        } catch (IOException e) {
            return null;
        }
    }

    private boolean isConnectedToInternet() {
        boolean onlineCheck = WikiConnection.isOnline();
        return !onlineCheck;
    }

    private List<Revision> sortRevisionList(List<Revision> revisionList) {
        RevisionSorter revisionSorter = new RevisionSorter();
        revisionList = revisionSorter.sortByMostRevisions(revisionList);
        return revisionList;
    }

    private List<Revision> groupRevisionList(List<Revision> revisionList) {
        RevisionSorter revisionSorter = new RevisionSorter();
        revisionList = revisionSorter.groupByIP(revisionList);
        return revisionList;
    }

    private List<Revision> retrieveRevisionList(InputStream inputStream) {
        RevisionParser revisionParser = new RevisionParser();
        return revisionParser.parse(inputStream);
    }

    private String checkForRedirects(InputStream inputStream) {
        RedirectParser redirectParser = new RedirectParser();
        Redirect redirect = redirectParser.parse(inputStream);

        if (redirect == null){
            return "";
        } else if(redirect.isRedirected()){
            return "You have been redirected from " + redirect.getOriginalSearchTerm() + " to " + redirect.getRedirectSearchTerm() + "\n\n";
        } else {
            return "";
        }
    }

    private String printList(List<Revision> revisionList, String redirectMessage) {
        StringBuilder outputString = new StringBuilder(redirectMessage);

        if(revisionList == null) {
            return "No Results Found";
        }

        for (Revision revision : revisionList){
            outputString.append("User: ").append(revision.getUser()).append("\nDate: ").append(revision.getTimestamp()).append("\n\n");
        }

        return outputString.toString();
    }
}
