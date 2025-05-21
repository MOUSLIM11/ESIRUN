package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import java.time.LocalDate;
import java.time.Period;

public class DisplayUsersView extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        Label headerLabel = new Label("Registered Users");
        headerLabel.getStyleClass().add("header-label");

        ListView<String> usersListView = new ListView<>();
        for (Personne personne : Service.getInstance().getVoyageurs()) {
            String userType = (personne instanceof Employe) ? "Employee" : "Regular User";
            String handicapStatus = personne.isHandicap() ? "Handicapped" : "Not Handicapped";
            int age = Period.between(personne.getDateNaissance(), LocalDate.now()).getYears();
            
            if (personne instanceof Employe) {
                Employe employe = (Employe) personne;
                usersListView.getItems().add(String.format(
                    "Name: %s %s | Age: %d | Type: %s (%s) | Status: %s",
                    employe.getNom(),
                    employe.getPrenom(),
                    age,
                    userType,
                    employe.getFonction(),
                    handicapStatus
                ));
            } else {
                usersListView.getItems().add(String.format(
                    "Name: %s %s | Age: %d | Type: %s | Status: %s",
                    personne.getNom(),
                    personne.getPrenom(),
                    age,
                    userType,
                    handicapStatus
                ));
            }
        }
        usersListView.setPrefHeight(400);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        root.getChildren().addAll(
            headerLabel,
            usersListView,
            closeButton
        );

        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Display Users");
        stage.show();
    }
} 