package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MoyenTransport;
import model.Service;
import model.Station;

public class AddStation implements EventHandler<ActionEvent> {

    private String type;
    private String identifiant;

    public AddStation(String type, String identifiant) {
        this.type = type;
        this.identifiant = identifiant;
    }

    @Override
    public void handle(ActionEvent event) {
        Service service = Service.getInstance();
        String message;

        if (identifiant == null || identifiant.trim().isEmpty()) {
            showAlert("L'identifiant ne peut pas être vide.");
            return;
        }

        if (type.equals("Station")) {
            Station station = new Station(identifiant);
            if (service.getStations().stream().anyMatch(s -> s.getNom().equalsIgnoreCase(identifiant))) {
                message = "La station existe déjà.";
            } else {
                service.getStations().add(station);
                message = "Station ajoutée : " + station;
            }
        } 
        else if (type.equals("MoyenTransport")) {
            MoyenTransport transport = new MoyenTransport(identifiant);
            if (service.getMoyens().stream().anyMatch(m -> m.getIdentifiant().equalsIgnoreCase(identifiant))) {
                message = "Le moyen de transport existe déjà.";
            } else {
                service.getMoyens().add(transport);
                message = "Moyen de transport ajouté : " + transport;
            }
        } 
        else {
            message = "Type invalide.";
        }

        showAlert(message);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
