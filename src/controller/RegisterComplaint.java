package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.*;
import java.time.LocalDate;

public class RegisterComplaint implements EventHandler<ActionEvent> {

    private TypeReclamation type;
    private Suspendable cible;
    private String description;
    private Personne personne;

    public RegisterComplaint(TypeReclamation type, Suspendable cible, String description, Personne personne) {
        this.type = type;
        this.cible = cible;
        this.description = description;
        this.personne = personne;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            Reclamation reclamation = new Reclamation(personne, type, cible, description, LocalDate.now());
            ServiceReclamation.getInstance().soumettre(reclamation);
            Service.getInstance().addReclamation(reclamation);
            
            showSuccessAlert("Complaint registered successfully by " + personne.getNom() + " " + personne.getPrenom());
        } catch (Exception ex) {
            showErrorAlert("Error registering complaint: " + ex.getMessage());
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Complaint Registered");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Registration Failed");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
