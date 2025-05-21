package view;

import controller.AddUser;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Fonction;
import java.time.LocalDate;

public class AddUserView extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        Label headerLabel = new Label("Add New User");
        headerLabel.getStyleClass().add("header-label");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Name field
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        // Prenom field
        Label prenomLabel = new Label("Prenom:");
        TextField prenomField = new TextField();
        grid.add(prenomLabel, 0, 1);
        grid.add(prenomField, 1, 1);

        // Birthday field
        Label birthdayLabel = new Label("Birthday:");
        DatePicker birthdayPicker = new DatePicker();
        grid.add(birthdayLabel, 0, 2);
        grid.add(birthdayPicker, 1, 2);

        // Handicap checkbox
        CheckBox handicapCheck = new CheckBox("Handicapped");
        grid.add(handicapCheck, 1, 3);

        // Employee checkbox
        CheckBox employeeCheck = new CheckBox("Is Employee");
        grid.add(employeeCheck, 1, 4);

        // Employee-specific fields (initially disabled)
        Label matriculeLabel = new Label("Matricule:");
        TextField matriculeField = new TextField();
        grid.add(matriculeLabel, 0, 5);
        grid.add(matriculeField, 1, 5);
        matriculeLabel.setVisible(false);
        matriculeField.setVisible(false);

        ComboBox<Fonction> fonctionCombo = new ComboBox<>();
        fonctionCombo.getItems().addAll(Fonction.AGENT, Fonction.CONDUCTEUR);
        grid.add(fonctionCombo, 1, 6);
        fonctionCombo.setVisible(false);

        // Show/hide employee fields based on checkbox
        employeeCheck.setOnAction(e -> {
            boolean isEmployee = employeeCheck.isSelected();
            matriculeLabel.setVisible(isEmployee);
            matriculeField.setVisible(isEmployee);
            fonctionCombo.setVisible(isEmployee);
        });

        Button submitButton = new Button("Add User");
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String prenom = prenomField.getText();
            LocalDate birthday = birthdayPicker.getValue();
            boolean isHandicap = handicapCheck.isSelected();
            boolean isEmployee = employeeCheck.isSelected();
            String matricule = matriculeField.getText();
            Fonction fonction = fonctionCombo.getValue();

            if (name.isEmpty() || prenom.isEmpty() || birthday == null || 
                (isEmployee && (matricule.isEmpty() || fonction == null))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please fill all required fields");
                alert.showAndWait();
                return;
            }

            new AddUser(name, prenom, birthday, isHandicap, isEmployee, matricule, fonction).handle(e);
            stage.close();
        });

        root.getChildren().addAll(headerLabel, grid, submitButton);

        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Add New User");
        stage.show();
    }
} 