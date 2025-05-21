package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import controller.ValidateFareMedia;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

public class ValidateFareMediaView {

    public void start(Stage stage) {
        // Main container
        VBox root = new VBox(30);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("root");

        // Header
        Label headerLabel = new Label("Transport Title Validation");
        headerLabel.getStyleClass().add("header-label");

        // Form section
        VBox formSection = new VBox(20);
        formSection.getStyleClass().add("validation-section");
        formSection.setMaxWidth(Region.USE_PREF_SIZE);

        // Form title
        Label formTitle = new Label("Enter Title Details");
        formTitle.getStyleClass().add("form-title");

        // Form container
        GridPane formGrid = new GridPane();
        formGrid.setHgap(25);
        formGrid.setVgap(20);
        formGrid.setAlignment(Pos.CENTER);
        formGrid.getStyleClass().add("form-grid");

        // Title type field
        Label typeLabel = new Label("Title Type:");
        typeLabel.getStyleClass().add("field-label");
        ComboBox<String> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll("Ticket", "Navigation Card");
        typeCombo.setPrefWidth(300);
        typeCombo.setPromptText("Select title type");

        // Title ID field
        Label idLabel = new Label("Title ID:");
        idLabel.getStyleClass().add("field-label");
        TextField idField = new TextField();
        idField.setPrefWidth(300);
        idField.setPromptText("Enter title ID");

        // Add components to grid with proper spacing
        formGrid.add(typeLabel, 0, 0);
        formGrid.add(typeCombo, 1, 0);
        formGrid.add(idLabel, 0, 1);
        formGrid.add(idField, 1, 1);

        // Validation section
        VBox validationSection = new VBox(20);
        validationSection.setAlignment(Pos.CENTER);
        validationSection.setPadding(new Insets(20, 0, 0, 0));

        // Validation button
        Button validateButton = new Button("Validate Transport Title");
        validateButton.getStyleClass().addAll("button", "validate-button");
        
        validateButton.setOnAction(e -> {
            String type = typeCombo.getValue();
            String idText = idField.getText();

            if (type == null || type.isEmpty()) {
                showAlert("Please select the title type.");
                return;
            }

            if (idText.isEmpty()) {
                showAlert("Please enter the title ID.");
                return;
            }

            try {
                int id = Integer.parseInt(idText);
                ValidateFareMedia handler = new ValidateFareMedia(type, id);
                handler.handle(e);
            } catch (NumberFormatException ex) {
                showAlert("The ID must be a valid number.");
            }
        });

        // Add components to validation section
        validationSection.getChildren().add(validateButton);

        // Add all components to the form section
        formSection.getChildren().addAll(formTitle, formGrid, validationSection);

        // Add everything to root
        root.getChildren().addAll(headerLabel, formSection);

        // Make root expand to fill window
        VBox.setVgrow(formSection, Priority.ALWAYS);

        // Create scene with responsive sizing
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Transport Title Validation");
        stage.setMinWidth(600);
        stage.setMinHeight(500);
        stage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStyleClass().add("dialog-pane");
        alert.showAndWait();
    }
}
