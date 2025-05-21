package view;

import controller.RegisterComplaint;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;
import java.util.TreeSet;

public class RegisterComplaintView extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        Label headerLabel = new Label("Register a Complaint");
        headerLabel.getStyleClass().add("header-label");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Type selection
        Label typeLabel = new Label("Complaint Type:");
        ComboBox<TypeReclamation> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll(TypeReclamation.TECHNIQUE, TypeReclamation.SERVICE);
        grid.add(typeLabel, 0, 0);
        grid.add(typeCombo, 1, 0);

        // Target selection
        Label cibleLabel = new Label("Target:");
        ComboBox<Suspendable> cibleCombo = new ComboBox<>();
        TreeSet<Suspendable> suspendables = new TreeSet<>();
        suspendables.addAll(Service.getInstance().getMoyens());
        suspendables.addAll(Service.getInstance().getStations());
        cibleCombo.getItems().addAll(suspendables);
        grid.add(cibleLabel, 0, 1);
        grid.add(cibleCombo, 1, 1);

        // User selection
        Label userLabel = new Label("Select User:");
        ComboBox<Personne> userCombo = new ComboBox<>();
        userCombo.getItems().addAll(Service.getInstance().getVoyageurs());
        grid.add(userLabel, 0, 2);
        grid.add(userCombo, 1, 2);

        // Description
        Label descriptionLabel = new Label("Description:");
        TextArea descriptionArea = new TextArea();
        descriptionArea.setPrefRowCount(3);
        descriptionArea.setWrapText(true);
        grid.add(descriptionLabel, 0, 3);
        grid.add(descriptionArea, 1, 3);

        Button submitButton = new Button("Submit Complaint");
        submitButton.setOnAction(e -> {
            TypeReclamation selectedType = typeCombo.getValue();
            Suspendable selectedCible = cibleCombo.getValue();
            String description = descriptionArea.getText();
            Personne selectedUser = userCombo.getValue();

            if (selectedType == null || selectedCible == null || description.isEmpty() || selectedUser == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please fill all required fields");
                alert.showAndWait();
                return;
            }

            new RegisterComplaint(selectedType, selectedCible, description, selectedUser).handle(e);
            stage.close();
        });

        root.getChildren().addAll(headerLabel, grid, submitButton);

        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Register Complaint");
        stage.show();
    }
}
