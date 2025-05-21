package model;

import java.time.*;
import java.util.TreeSet;
import java.io.Serializable;

public abstract class Personne implements Comparable<Personne>, Serializable {
    private static final long serialVersionUID = 1L;
 
    protected  String nom;
    protected  String prenom;
    protected  LocalDate dateNaissance;
    protected boolean handicap;
    private TreeSet<NavigationCard> cards;

    public Personne(String nom, String prenom, LocalDate dateNaissance, boolean handicap) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.handicap = handicap;
        this.cards= new TreeSet<NavigationCard>();
    }
    
    public Personne(String name)
    {
        this.nom = name;
        this.prenom = null;
        this.dateNaissance = null;
        this.cards= new TreeSet<NavigationCard>();
        
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public boolean isHandicap() {
        return handicap;
    }

    public void setHandicap(boolean handicap) {
        this.handicap = handicap;
    }
    
    

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
    
    public TreeSet<NavigationCard> getCards(){
        return this.cards;
    }
    
    public void acheter(NavigationCard t)
    {
        this.cards.add(t);
    }

    @Override
    public int compareTo(Personne other) {
        int nameComparison = this.nom.compareTo(other.nom);
        if (nameComparison != 0) {
            return nameComparison;
        }
        return this.prenom.compareTo(other.prenom);
    }
}
