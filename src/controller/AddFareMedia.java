/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.*;

/**
 *
 * @author RCS
 */
public class AddFareMedia implements EventHandler<ActionEvent> {
    
    private Personne voyageur;
    private PaymentMethod paymentMethod;
    private boolean isTicket;
    private TypeCarte typeCarte;

    public AddFareMedia(Personne voyageur, PaymentMethod paymentMethod, boolean isTicket) {
        this.voyageur = voyageur;
        this.paymentMethod = paymentMethod;
        this.isTicket = isTicket;
    }

    public AddFareMedia(PaymentMethod paymentMethod, boolean isTicket) {
        this.paymentMethod = paymentMethod;
        this.isTicket = isTicket;
    }
    
    public AddFareMedia(Employe voyageur, PaymentMethod paymentMethod, boolean isTicket) {
        this.voyageur = voyageur;
        this.paymentMethod = paymentMethod;
        this.isTicket = isTicket;
    }
    
    

    
    
    
    @Override
    public void handle(ActionEvent event)
    {
        Service service = Service.getInstance();
        
        try {
            if (isTicket) {
                Ticket ticket = new Ticket(this.paymentMethod);
                service.addTicket(ticket);
                showSuccessAlert("Ticket created successfully with ID: " + ticket.getId());
            } else {
                if (voyageur == null) {
                    showErrorAlert("No user selected for navigation card.");
                    return;
                }
                NavigationCard card = new NavigationCard(this.voyageur, this.paymentMethod);
                service.addCard(card);
                voyageur.acheter(card);
                showSuccessAlert("Navigation card created successfully for " + voyageur.getNom() + " " + voyageur.getPrenom());
            }
        } catch (Exception ex) {
            showErrorAlert("Error creating fare medium: " + ex.getMessage());
        }
    }   

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Fare Medium Created");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Creation Failed");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
