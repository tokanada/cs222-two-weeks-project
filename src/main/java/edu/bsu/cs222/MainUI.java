package edu.bsu.cs222;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;


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
        button.setOnAction(event -> { outputField.setText("Clicked it"); });
        return new Scene(new VBox(
                new Label("Enter your JSON below"),
                jsonArea,
                button,
                outputField));
    }
}

