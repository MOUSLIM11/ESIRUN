package model;
import java.time.LocalDate;
import java.io.Serializable;

public class Reclamation implements Comparable<Reclamation>, Serializable {
    private static final long serialVersionUID = 1L;
    private static int compteur = 1; 
    private int numero;
    private LocalDate date;
    private Personne personne;
    private TypeReclamation type;
    private Suspendable cible;
    private String description;


    public Reclamation(Personne personne, TypeReclamation type, Suspendable cible, String description, LocalDate date) {
        this.personne = personne;
        this.type = type;
        this.cible = cible;
        this.description = description;
        this.date = date;
        this.numero = compteur;
        compteur++;
    }

    
    @Override
     public int compareTo(Reclamation other) {
        int dateComparison = this.date.compareTo(other.date);
        if (dateComparison != 0) {
            return dateComparison;
        }
        return this.description.compareTo(other.description);
    }
    public Suspendable getCible() { return cible; }
    public int getNumero() { return numero; }
    public LocalDate getDate() { return date; }
    public Personne getPersonne() { return personne; }
    public String getDescription() { return description; }
    public TypeReclamation getType() {return type;}
    public String toString(){ return("Date: "+this.date+" | Type: "+ this.type+" | Cible: "+ this.cible+" | Description: "+this.description+" | Soumise par: "+ this.personne);
    }
      
    }
