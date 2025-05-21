package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.NavigationCard;
import model.Service;
import model.Ticket;

public class ValidateFareMedia implements EventHandler<ActionEvent> {

    private String type;
    private int id;

    public ValidateFareMedia(String type, int id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void handle(ActionEvent event) {
        Service service = Service.getInstance();
        String message = "";

        if (type.equals("Ticket")) {
            Ticket ticket = service.getTickets().stream().filter(t -> t.getId() == id).findAny().orElse(null);

            if(ticket == null)
            {
                message = "Ce ticket n'existe pas.";
            }
            else if(!ticket.valide())
            {
                message = "Ce ticket est invalide";
            }
            else
            {
                message = "Votre ticket est valide";
                ticket.setValide(false);
            }

        } else if (type.equals("Navigation Card")) {
            NavigationCard card = service.getNavigationCards().stream().filter(c -> c.getId() == id).findFirst().orElse(null);
            
            if(card == null)
            {
                message = "Cette carte personnelle n'existe pas.";
            }
            else if(!card.valide())
            {
                message = "Cett carte est invalide";
            }
            else
            {
                message = "Votre carte personnelle est valide";
            }
        }

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Validation du Titre");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
