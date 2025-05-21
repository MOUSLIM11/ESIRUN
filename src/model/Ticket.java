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
public class Ticket extends TitreTransport {
    public static final double somme = 50.0;

    public Ticket(PaymentMethod paymentMethod) {
        super(paymentMethod);
        this.valide = true;
        this.prix = Ticket.somme;
    }

    

    public String estValide(LocalDate date) throws TitreNonValideException{
        if(!(date.isEqual(this.dateAchat))){ throw new TitreNonValideException("Ticket numéro " + this.getId() + " expiré - valable uniquement le : " + this.getDateAchat()); }
        return "valide";
    }
    
    public boolean valide()
    {
        this.valide = LocalDate.now().isEqual(this.dateAchat) && this.valide;
        return this.valide;
        
    }
    

    
    
    

    
    
    
    
}
