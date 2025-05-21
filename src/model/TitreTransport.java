package model;

import java.time.*;
import java.io.Serializable;



// Classe abstraite pour les titres de transport
public abstract class TitreTransport implements Comparable<TitreTransport>, Serializable {
    private static final long serialVersionUID = 1L;
    
    public static int counter = 1;   
    protected int id;
    protected LocalDate dateAchat;
    protected double prix;
    protected PaymentMethod paymentMethod;
    protected boolean valide;
    
            
    abstract public String estValide(LocalDate date) throws TitreNonValideException;
    @Override
    public int compareTo(TitreTransport other) {
        return Integer.compare(this.id, other.id);
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        TitreTransport.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TitreTransport(PaymentMethod paymentMethod) {
        this.id = TitreTransport.counter++;
        this.dateAchat = LocalDate.now();
        this.paymentMethod = paymentMethod;
    }

    public TitreTransport() {
        this.id = TitreTransport.counter++;
        this.dateAchat = LocalDate.now();
    }

    public boolean isValide() {
        return valide;
    }

    public void setValide(boolean valide) {
        this.valide = valide;
    }
    
    public abstract boolean valide();
    
    
    
    
}