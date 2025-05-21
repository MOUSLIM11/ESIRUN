package controller;

import model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import java.time.LocalDate;

public class AddUser implements EventHandler<ActionEvent> {
    private String name;
    private String prenom;
    private LocalDate birthday;
    private boolean isHandicap;
    private boolean isEmployee;
    private String matricule;
    private Fonction fonction;

    public AddUser(String name, String prenom, LocalDate birthday, boolean isHandicap, boolean isEmployee, String matricule, Fonction fonction) {
        this.name = name;
        this.prenom = prenom;
        this.birthday = birthday;
        this.isHandicap = isHandicap;
        this.isEmployee = isEmployee;
        this.matricule = matricule;
        this.fonction = fonction;
    }

    @Override
    public void handle(ActionEvent event) {
        Service service = Service.getInstance();
        
        if (isEmployee) {
            Employe employe = new Employe(matricule, fonction, name, prenom, birthday, isHandicap);
            service.addVoyageur(employe);
        } else {
            Usager voyageur = new Usager(name, prenom, birthday, isHandicap);
            service.addVoyageur(voyageur);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("User Created");
        alert.setContentText("User " + name + " " + prenom + " has been successfully added.");
        alert.showAndWait();
    }
} 