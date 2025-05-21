package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.NavigationCard;
import model.Service;
import model.Ticket;
import model.TitreTransport;
import java.util.TreeSet;

public class DisplayFareMedia implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent e) {
        Service service = Service.getInstance();
        TreeSet<TitreTransport> titres = new TreeSet<>();
        titres.addAll(service.getTickets());
        titres.addAll(service.getNavigationCards());

        StringBuilder mediaList = new StringBuilder();

        for (TitreTransport titre : titres) {
            if (titre instanceof Ticket) {
                mediaList.append("Type: Ticket | ")
                        .append("Date: ").append(titre.getDateAchat()).append(" | ")
                        .append("Payment: ").append(titre.getPaymentMethod()).append("\n");
            } 
            else if (titre instanceof NavigationCard) {
                NavigationCard card = (NavigationCard) titre;
                String fullName = card.getProprietaire().getNom() + " " + card.getProprietaire().getPrenom();
                mediaList.append("Type: ").append(card.getTypeCarte()).append(" | ")
                        .append("Voyageur: ").append(fullName).append(" | ")
                        .append("Date: ").append(card.getDateAchat()).append(" | ")
                        .append("Payment: ").append(card.getPaymentMethod()).append("\n");
            }
        }

        if (mediaList.length() == 0) {
            mediaList.append("Aucun titre de transport vendu.");
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fare Media List");
        alert.setHeaderText("List of Sold Fare Media");
        alert.setContentText(mediaList.toString());
        alert.showAndWait();
    }
}
