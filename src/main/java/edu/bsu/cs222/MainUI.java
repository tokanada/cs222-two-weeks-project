package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class MainUI extends Application {
    private final Label pageField = new Label("Enter a Wikipedia Page Title to grab data from:");
    private final TextField searchBox = new TextField();
    private final Button searchByTimeButton = new Button("Search - Sort by Time");
    private final Button searchByFrequencyButton = new Button("Search - Sort by Frequency");
    private final Button searchByGroupingButton = new Button("Search - Group by IP");
    private final TextArea outputArea = new TextArea();
    private final HBox windowArea = new HBox();
    private final VBox buttonArea = new VBox(searchByTimeButton, searchByFrequencyButton, searchByGroupingButton);
    private final VBox headerArea = new VBox(pageField, searchBox);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMinWidth(350);
        primaryStage.setMinHeight(700);
        primaryStage.setTitle("WikiReach - Revision Grabber");
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    private Scene createScene() {
        searchByTimeButton.setOnAction(event -> searchByTimeButtonEvent());
        searchByFrequencyButton.setOnAction(event -> searchByFrequencyButtonEvent());
        searchByGroupingButton.setOnAction(event -> searchByGroupingButtonEvent());
        configureButtonArea();
        configureHeaderArea();
        configureWindowArea();
        return new Scene(windowArea);
    }

    private void configureHeaderArea() {
        headerArea.setPadding(new Insets(10, 10, 10, 0));
        headerArea.setSpacing(10);
        headerArea.getChildren().add(buttonArea);
    }

    private void configureWindowArea() {
        windowArea.alignmentProperty().setValue(Pos.TOP_CENTER);
        windowArea.setPadding(new Insets(10, 10, 10, 10));
        windowArea.getChildren().add(headerArea);
        outputArea.setEditable(false);
        windowArea.getChildren().add(outputArea);
    }

    private void configureButtonArea() {
        buttonArea.setSpacing(5);
        buttonArea.alignmentProperty().setValue(Pos.CENTER);
    }

    private void searchByFrequencyButtonEvent() {
        WikiRevisionRequester requester = new WikiRevisionRequester();
        String textBoxInput = searchBox.getText();
        String output = requester.retrieveResultsByFrequency(textBoxInput);
        outputArea.setText(output);
    }

    private void searchByTimeButtonEvent() {
        WikiRevisionRequester requester = new WikiRevisionRequester();
        String textBoxInput = searchBox.getText();
        String output = requester.retrieveResultsByTime(textBoxInput);
        outputArea.setText(output);
    }

    private void searchByGroupingButtonEvent() {
        WikiRevisionRequester requester = new WikiRevisionRequester();
        String textBoxInput = searchBox.getText();
        String output = requester.retrieveResultsByGrouping(textBoxInput);
        outputArea.setText(output);
    }

}

