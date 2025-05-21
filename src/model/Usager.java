package model;

import java.time.*;

public class Usager extends Personne {
    
    private int id;

    
    public Usager(String nom, String prenom, LocalDate deteNaissance, boolean handicap) {
        super(nom, prenom, deteNaissance, handicap);
        this.id = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    
    
}