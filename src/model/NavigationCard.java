/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.*;

/**
 *
 * @author RCS
 */
public class NavigationCard extends TitreTransport {
    private Personne Proprietaire;
    private TypeCarte typeCarte;
    private static final double somme= 5000.0;

    public NavigationCard(Personne Proprietaire, PaymentMethod p) throws ReductionImpossibleException{

        super(p);
        this.valide = true;
        this.Proprietaire = Proprietaire;
        double mainPrice = 5000;
        TypeCarte bestCarte;
        double bestPrice = mainPrice;

        if(this.Proprietaire.handicap == true){
            bestPrice = mainPrice*50/100;
            bestCarte = TypeCarte.SOLIDARITE;
        }else{
            if(this.Proprietaire instanceof Employe){
                bestPrice = mainPrice*60/100;
                bestCarte = TypeCarte.PARTENAIRE;
            }else{
                if(Period.between(this.Proprietaire.dateNaissance, LocalDate.now()).getYears() < 25){
                    bestPrice = mainPrice*70/100;
                    bestCarte = TypeCarte.JUNIOR;
                }else{
                    if(Period.between(this.Proprietaire.dateNaissance, LocalDate.now()).getYears() > 65){
                        bestPrice = mainPrice*75/100;
                        bestCarte = TypeCarte.SENIOR;
                    }else{ throw new ReductionImpossibleException();}
                }

            }

        }


        this.setPrix(bestPrice);
        this.typeCarte = bestCarte;
    }


    @Override
    public String estValide(LocalDate date) throws TitreNonValideException{
        if(Period.between(this.dateAchat, date).getYears()>=1){
            throw new TitreNonValideException("Carte personnelle numéro " + this.getId() + " invalide");}
        return "valide";
    }
    
    @Override
    public boolean valide()
    {
        this.valide = Period.between(this.dateAchat, LocalDate.now()).getYears()<1;
        return this.valide;
    }

    public TypeCarte getTypeCarte() {
        return typeCarte;
    }


    public Personne getProprietaire() {
        return Proprietaire;
    }

    public void setProprietaire(Personne Proprietaire) {
        this.Proprietaire = Proprietaire;
    }
    
    
    
    
}
