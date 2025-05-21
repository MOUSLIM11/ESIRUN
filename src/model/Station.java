package model;

import java.io.Serializable;

public class Station implements Suspendable, Comparable<Station>, Serializable {
    private static final long serialVersionUID = 1L;
    private String nom;
    private boolean suspendu;
    public Station(String nom) {
        this.nom = nom;
        suspendu = false;
    }

    public String getNom() { return nom;  }

    @Override
    public String toString() { return "Station de " + nom ;}
    
    @Override
    public void suspendre()
    {
        this.suspendu = true;
    }
    
    @Override
    public void reactiver()
    {
        this.suspendu = false;
    }
    
    @Override
    public boolean estSuspendu()
    {
        return this.suspendu;
    }
    
    @Override
    public String getEtat()
    {
        if(this.suspendu)
        {
            return "suspendu";
        }
        else
        {
            return "actif";
        }
    }

    @Override
    public int compareTo(Station other) {
        return this.nom.compareTo(other.nom);
    }
    
}
