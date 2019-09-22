package edu.bsu.cs222.app;

import edu.bsu.cs222.Revision;
import edu.bsu.cs222.RevisionParser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class MainUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    private Scene createScene() {
        final TextArea jsonArea = new TextArea();
        final Button button = new Button("Parse");
        final TextField outputField = new TextField();
        outputField.setEditable(false);
        button.setOnAction(event -> {
            RevisionParser parser = new RevisionParser();
            InputStream textBoxInput = new ByteArrayInputStream(jsonArea.getText().getBytes(StandardCharsets.UTF_8));
            List<Revision> list = null;
            try {
                list = parser.parse(textBoxInput);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText(e.getMessage());
                alert.show();
            }
            outputField.setText("There were " + list.size() + " revisions found");
        });
        return new Scene(new VBox(
                new Label("Enter your JSON below"),
                jsonArea,
                button,
                outputField));
    }
}

