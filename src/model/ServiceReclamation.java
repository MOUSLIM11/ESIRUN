package model;
import java.util.*;

public class ServiceReclamation {
    private static ServiceReclamation instance;
    private final int SEUIL = 3;
    private Map<TypeReclamation, TreeSet<Reclamation>> reclamationsParType = new TreeMap<>();
    private Map<Personne, TreeSet<Reclamation>> reclamationsParPersonne = new HashMap<>();
    private Map<Suspendable, TreeSet<Reclamation>> reclamationsParSuspendable = new HashMap<>();

    
        public static ServiceReclamation getInstance()
    {
        if(instance == null)
        {
            instance = new ServiceReclamation();
        }
        return instance;
    }
        
        
    public void afficherReclamations() {
        System.out.println("            Reclamations de type TECHNIQUE");
        if (reclamationsParType.containsKey(TypeReclamation.TECHNIQUE)) {
            TreeSet<Reclamation> reclamations = reclamationsParType.get(TypeReclamation.TECHNIQUE);
            for (Reclamation r : reclamations) {
                System.out.println("Reclamation#" + r.getNumero());
                System.out.println(r.toString());
                System.out.println();
            }
        }
        
        System.out.println("            Reclamations de type SERVICE");
        if (reclamationsParType.containsKey(TypeReclamation.SERVICE)) {
            TreeSet<Reclamation> reclamations = reclamationsParType.get(TypeReclamation.SERVICE);
            for (Reclamation r : reclamations) {
                System.out.println("Reclamation#" + r.getNumero());
                System.out.println(r.toString());
                System.out.println();
            }
        }
    }
    
    
    public void afficherReclamations(Personne personne) {
        if (reclamationsParPersonne.containsKey(personne)) {
            TreeSet<Reclamation> reclamations = reclamationsParPersonne.get(personne);
            for (Reclamation r : reclamations) {
                System.out.println("Reclamation#" + r.getNumero());
                System.out.println(r.toString());
                System.out.println();
            }
        }
    }
    
    public void afficherReclamations(Suspendable moyen) {
        if (reclamationsParSuspendable.containsKey(moyen)) {
            TreeSet<Reclamation> reclamations = reclamationsParSuspendable.get(moyen);
            for (Reclamation r : reclamations) {
                System.out.println("Reclamation#" + r.getNumero());
                System.out.println(r.toString());
                System.out.println();
            }
        }
    }
    
    public void soumettre(Reclamation r) {
       
        if (!reclamationsParType.containsKey(r.getType())) {
            reclamationsParType.put(r.getType(), new TreeSet<>());
        }
        reclamationsParType.get(r.getType()).add(r);
        
        
        if (!reclamationsParPersonne.containsKey(r.getPersonne())) {
            reclamationsParPersonne.put(r.getPersonne(), new TreeSet<>());
        }
        reclamationsParPersonne.get(r.getPersonne()).add(r);
        
       
        if (!reclamationsParSuspendable.containsKey(r.getCible())) {
            reclamationsParSuspendable.put(r.getCible(), new TreeSet<>());
        }
        reclamationsParSuspendable.get(r.getCible()).add(r);
        
       
        if (reclamationsParSuspendable.get(r.getCible()).size() >= SEUIL) {
            r.getCible().suspendre();
        }
    }
    
     public void resoudre(Reclamation reclamation) {
       
        if (reclamationsParSuspendable.containsKey(reclamation.getCible()) && 
            reclamation.getCible().estSuspendu()) {
            
            
            if (reclamationsParType.containsKey(reclamation.getType())) {
                reclamationsParType.get(reclamation.getType()).remove(reclamation);
            }
            
            if (reclamationsParPersonne.containsKey(reclamation.getPersonne())) {
                reclamationsParPersonne.get(reclamation.getPersonne()).remove(reclamation);
            }
            
            if (reclamationsParSuspendable.containsKey(reclamation.getCible())) {
                reclamationsParSuspendable.get(reclamation.getCible()).remove(reclamation);
            }
            
            
             if (reclamationsParSuspendable.get(reclamation.getCible()).size() < SEUIL) {
                
                reclamation.getCible().reactiver();
            }
        }
    }  
}