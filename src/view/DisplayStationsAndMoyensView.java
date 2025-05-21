package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class DisplayStationsAndMoyensView extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        Label headerLabel = new Label("Stations and Transport Means");
        headerLabel.getStyleClass().add("header-label");

        // Stations Section
        Label stationsLabel = new Label("Stations");
        stationsLabel.getStyleClass().add("section-header");
        ListView<String> stationsListView = new ListView<>();
        for (Station station : Service.getInstance().getStations()) {
            stationsListView.getItems().add(String.format("Station: %s | Status: %s", 
                station.getNom(), 
                station.getEtat()));
        }
        stationsListView.setPrefHeight(200);

        // Transport Means Section
        Label moyensLabel = new Label("Transport Means");
        moyensLabel.getStyleClass().add("section-header");
        ListView<String> moyensListView = new ListView<>();
        for (MoyenTransport moyen : Service.getInstance().getMoyens()) {
            moyensListView.getItems().add(String.format("Transport: %s | Status: %s", 
                moyen.getIdentifiant(), 
                moyen.getEtat()));
        }
        moyensListView.setPrefHeight(200);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(
            headerLabel,
            stationsLabel,
            stationsListView,
            moyensLabel,
            moyensListView,
            closeButton
        );

        Scene scene = new Scene(root, 500, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Display Stations and Transport Means");
        stage.show();
    }
} 