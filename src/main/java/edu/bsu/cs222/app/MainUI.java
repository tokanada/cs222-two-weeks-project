package edu.bsu.cs222.app;

import edu.bsu.cs222.Revision;
import edu.bsu.cs222.RevisionParser;
import edu.bsu.cs222.WikiConnection;
import edu.bsu.cs222.WikiURLEncoder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class MainUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    private Scene createScene() {
        final TextField jsonArea = new TextField();
        final Button searchButton = new Button("Search");
        final Button sortButton = new Button("Sort");
        final TextArea outputField = new TextArea();
        outputField.setEditable(false);
        searchButton.setOnAction(event -> {
            String textBoxInput = jsonArea.getText();
            String output = searchButtonPressEvent(textBoxInput);
            outputField.setText(output);
        });

        return new Scene(new VBox(
                new Label("Enter your Search Term below"),
                jsonArea,
                searchButton, sortButton,
                outputField));
    }

    private String searchButtonPressEvent(String textBoxInput) {
        List<Revision> revisionList = new ArrayList<>();
        String output = "";
        if(!isConnectedToInternet()){
            output = "Could Not Connect to Wikipedia.\nPlease check your connection and try again.";
        }else {
            String isRedirected = checkForRedirects();
            revisionList = retrieveRevisionList(revisionList, textBoxInput);
            output = printList(revisionList);
        }
        return output;
    }

    private String checkForRedirects() {
        RevisionParser parser = new RevisionParser();
        if(parser.isRedirected()){
            return "You have been redirected to "
        }
    }

    private boolean isConnectedToInternet() {
        WikiConnection wikiConnection = new WikiConnection();
        boolean onlineCheck = wikiConnection.isOnline();
        return onlineCheck;
    }

    private List<Revision> retrieveRevisionList(List<Revision> revisionList, String inputString) {
        try {
            RevisionParser parser = new RevisionParser();
            String encodedSearch = WikiURLEncoder.encode(inputString);
            URLConnection urlConnection = WikiConnection.connectToWikipedia(encodedSearch);
            InputStream inputStream = urlConnection.getInputStream();
            revisionList = parser.parse(inputStream);
            return revisionList;
        } catch (IOException e) {
            return null;
        }
    }

    private String printList(List<Revision> revisionList) {
        String outputString = "";

        if(revisionList == null) {
            return "No Results Found";
        }

        for (Revision revision : revisionList){
            outputString += "User: " + revision.getUser() + "\nDate: " + revision.getTimestamp() + "\n\n";
        }

        return outputString;
    }


}

