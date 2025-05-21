package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import controller.AddStation;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AddStationView {

    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root");

        // Header
        Label headerLabel = new Label("Add Station or Transport");
        headerLabel.getStyleClass().add("header-label");

        // Form container
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setPadding(new Insets(20));
        formGrid.getStyleClass().add("form-grid");

        Label typeLabel = new Label("Type :");
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Station", "MoyenTransport");
        typeCombo.setPrefWidth(200);

        // Nom (pour Station)
        Label nameLabel = new Label("Name :");
        TextField nameField = new TextField();
        nameField.setPrefWidth(200);
        nameLabel.setVisible(false);
        nameLabel.setManaged(false);
        nameField.setVisible(false);
        nameField.setManaged(false);

        // Identifiant (pour MoyenTransport)
        Label idLabel = new Label("Identifiant :");
        TextField idField = new TextField();
        idField.setPrefWidth(200);
        idLabel.setVisible(false);
        idLabel.setManaged(false);
        idField.setVisible(false);
        idField.setManaged(false);

        // Add components to grid
        formGrid.add(typeLabel, 0, 0);
        formGrid.add(typeCombo, 1, 0);
        formGrid.add(nameLabel, 0, 1);
        formGrid.add(nameField, 1, 1);
        formGrid.add(idLabel, 0, 2);
        formGrid.add(idField, 1, 2);

        // Gestion du type sélectionné
        typeCombo.setOnAction(e -> {
            String selected = typeCombo.getValue();
            boolean isStation = "Station".equals(selected);

            nameLabel.setVisible(isStation);
            nameLabel.setManaged(isStation);
            nameField.setVisible(isStation);
            nameField.setManaged(isStation);

            idLabel.setVisible(!isStation);
            idLabel.setManaged(!isStation);
            idField.setVisible(!isStation);
            idField.setManaged(!isStation);
        });

        Button addButton = new Button("Ajouter");
        addButton.setPrefWidth(200);
        addButton.setOnAction((ActionEvent e) -> {
            String type = typeCombo.getValue();
            if (type == null) {
                showAlert("Veuillez sélectionner un type.");
                return;
            }

            if (type.equals("Station")) {
                String name = nameField.getText().trim();
                if (name.isEmpty()) {
                    showAlert("Veuillez entrer le nom de la station.");
                    return;
                }
                new AddStation(type, name).handle(e);
            } else {
                String id = idField.getText().trim();
                if (id.isEmpty()) {
                    showAlert("Veuillez entrer l'identifiant du moyen de transport.");
                    return;
                }
                new AddStation(type, id).handle(e);
            }
        });

        root.getChildren().addAll(headerLabel, formGrid, addButton);

        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Ajouter Station ou Transport");
        stage.setMinWidth(400);
        stage.setMinHeight(400);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStyleClass().add("dialog-pane");
        alert.showAndWait();
    }
}
