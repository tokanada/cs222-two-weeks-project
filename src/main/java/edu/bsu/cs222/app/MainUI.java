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
        final Button button1 = new Button("Search");
        final Button button2 = new Button("Sort");
        final TextArea outputField = new TextArea();
        outputField.setEditable(false);
        button1.setOnAction(event -> {
            List<Revision> revisionList = new ArrayList<>();
            String textBoxInput = jsonArea.getText();
            if(!isConnectedToInternet()){
                outputField.setText("Could Not Connect to Wikipedia.\nPlease check your connection and try again.");
            }else {
                revisionList = retrieveRevisionList(revisionList, textBoxInput);
                outputField.setText(printList(revisionList));
            }
        });
        return new Scene(new VBox(
                new Label("Enter your Search Term below"),
                jsonArea,
                button1, button2,
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

