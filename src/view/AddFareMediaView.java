package view;

import controller.AddFareMedia;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class AddFareMediaView extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("root");

        Label headerLabel = new Label("Add Fare Medium");
        headerLabel.getStyleClass().add("header-label");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Payment method selection
        Label paymentLabel = new Label("Payment Method:");
        ComboBox<PaymentMethod> paymentCombo = new ComboBox<>();
        paymentCombo.getItems().addAll(PaymentMethod.values());
        grid.add(paymentLabel, 0, 0);
        grid.add(paymentCombo, 1, 0);

        // Ticket or Card selection
        Label typeLabel = new Label("Type:");
        ToggleGroup typeGroup = new ToggleGroup();
        RadioButton ticketRadio = new RadioButton("Ticket");
        RadioButton cardRadio = new RadioButton("Navigation Card");
        ticketRadio.setToggleGroup(typeGroup);
        cardRadio.setToggleGroup(typeGroup);
        ticketRadio.setSelected(true);
        grid.add(typeLabel, 0, 1);
        grid.add(ticketRadio, 1, 1);
        grid.add(cardRadio, 1, 2);

        // User selection for navigation card
        Label userLabel = new Label("Select User:");
        ComboBox<Personne> userCombo = new ComboBox<>();
        userCombo.getItems().addAll(Service.getInstance().getVoyageurs());
        grid.add(userLabel, 0, 3);
        grid.add(userCombo, 1, 3);
        userLabel.setVisible(false);
        userCombo.setVisible(false);

        // Show/hide user selection based on card selection
        cardRadio.setOnAction(e -> {
            boolean isCard = cardRadio.isSelected();
            userLabel.setVisible(isCard);
            userCombo.setVisible(isCard);
        });

        ticketRadio.setOnAction(e -> {
            userLabel.setVisible(false);
            userCombo.setVisible(false);
        });

        Button submitButton = new Button("Add Fare Medium");
        submitButton.setOnAction(e -> {
            PaymentMethod paymentMethod = paymentCombo.getValue();
            boolean isTicket = ticketRadio.isSelected();
            Personne selectedUser = userCombo.getValue();

            if (paymentMethod == null || (!isTicket && selectedUser == null)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Input");
                alert.setContentText("Please fill all required fields");
                alert.showAndWait();
                return;
            }

            if (isTicket) {
                new AddFareMedia(paymentMethod, isTicket).handle(e);
            } else {
                new AddFareMedia(selectedUser, paymentMethod, isTicket).handle(e);
            }
            stage.close();
        });

        root.getChildren().addAll(headerLabel, grid, submitButton);

        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Add Fare Medium");
        stage.show();
    }
}
