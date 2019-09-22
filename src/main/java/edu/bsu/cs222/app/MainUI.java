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

import java.io.ByteArrayInputStream;
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
        final Button button = new Button("Search");
        final TextArea outputField = new TextArea();
        outputField.setEditable(false);
        button.setOnAction(event -> {
            List<Revision> revisionList = new ArrayList<>();
            String textBoxInput = jsonArea.getText();
            if(!isConnectedToInternet()){

            };
            revisionList = retrieveRevisionList(revisionList, textBoxInput);
            outputField.setText(printList(revisionList));
        });
        return new Scene(new VBox(
                new Label("Enter your Search Term below"),
                jsonArea,
                button,
                outputField));
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
        for (Revision revision : revisionList){
            outputString += "User: " + revision.getUser() + "\nDate: " + revision.getTimestamp() + "\n\n";
        }
        if(revisionList == null) {
            return null;
        }
        return outputString;
    }


}

