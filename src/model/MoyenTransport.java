package model;

import java.io.Serializable;

public class MoyenTransport implements Suspendable, Comparable<MoyenTransport>, Serializable {
    private static final long serialVersionUID = 1L;
    private String identifiant;
    private boolean suspendu;

    public MoyenTransport(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getIdentifiant() { return identifiant; }    
    @Override
    public String toString() { return identifiant ;}
    
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
    public int compareTo(MoyenTransport other) {
        return this.identifiant.compareTo(other.identifiant);
    }
  

}