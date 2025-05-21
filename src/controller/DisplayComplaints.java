package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import model.Reclamation;
import model.Service;

import java.util.List;
import java.util.TreeSet;
import model.Reclamation;

public class DisplayComplaints implements EventHandler<ActionEvent> {
    
@Override
public void handle(ActionEvent event) {
    TreeSet<Reclamation> r = Service.getInstance().getReclamations(); // Suppose cette méthode existe

    if (r.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Réclamations");
        alert.setHeaderText(null);
        alert.setContentText("Aucune réclamation enregistrée.");
        alert.showAndWait();
        return;
    }

    StringBuilder builder = new StringBuilder();
    for (Reclamation complaint : r) {
        String personName = complaint.getPersonne().getNom();
        String cible = complaint.getCible().toString();
        String description = complaint.getDescription();

        builder.append("Par : ").append(personName)
               .append("\nCible : ").append(cible)
               .append("\nDescription : ").append(description)
               .append("\n-------------------------\n");
    }

    TextArea output = new TextArea(builder.toString());
    output.setEditable(false);
    output.setWrapText(true);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Liste des Réclamations");
    alert.getDialogPane().setContent(output);
    alert.setResizable(true);
    alert.getDialogPane().setPrefSize(500, 400);
    alert.showAndWait();
}

}