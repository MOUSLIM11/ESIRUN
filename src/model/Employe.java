package model;

import java.time.LocalDate;

public class Employe extends Personne {
    private final String matricule;
    private final Fonction fonction;
//constructor
    public Employe(String matricule, Fonction fonction, String nom, String prenom, LocalDate dateNaissance, boolean handicap) {
        super(nom, prenom, dateNaissance, handicap);
        this.matricule = matricule;
        this.fonction = fonction;
    }

    

    public String getMatricule() { return matricule; }
    public Fonction getFonction() { return fonction; }
}
