/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.TreeSet;

/**
 *
 * @author RCS
 */
public class Service {
    
    private static Service instance;
    private TreeSet<Personne> voyageurs;
    private TreeSet<Ticket> tickets;
    private TreeSet<NavigationCard> navigationCards;
    private TreeSet<MoyenTransport> moyens;
    private TreeSet<Station> stations;
    private TreeSet<Reclamation> reclamations;

    private Service() {
        // Initialize empty collections first
        voyageurs = new TreeSet<>();
        tickets = new TreeSet<>();
        navigationCards = new TreeSet<>();
        moyens = new TreeSet<>();
        stations = new TreeSet<>();
        reclamations = new TreeSet<>();
        
        // Then load data from files
        loadAllData();
    }

    public static Service getInstance() {
        if(instance == null) {
            instance = new Service();
        }
        return instance;
    }
    
    private void loadAllData() {
        voyageurs = FileManager.loadUsers();
        reclamations = FileManager.loadComplaints();
        FileManager.loadFareMedia(this);
        FileManager.loadSuspendables(this);
    }
    
    private void saveAllData() {
        FileManager.saveUsers(voyageurs);
        FileManager.saveFareMedia(tickets, navigationCards);
        FileManager.saveComplaints(reclamations);
        FileManager.saveSuspendables(stations, moyens);
    }
    
    public void addVoyageur(Personne v) {
        voyageurs.add(v);
        FileManager.saveUsers(voyageurs);
    }
    
    public void addTicket(Ticket t) {
        tickets.add(t);
        FileManager.saveFareMedia(tickets, navigationCards);
    }
    
    public void addCard(NavigationCard c) {
        navigationCards.add(c);
        FileManager.saveFareMedia(tickets, navigationCards);
    }
    
    public void addStation(Station s) {
        stations.add(s);
        FileManager.saveSuspendables(stations, moyens);
    }
    
    public void addMoyen(MoyenTransport m) {
        moyens.add(m);
        FileManager.saveSuspendables(stations, moyens);
    }

    public void addReclamation(Reclamation r) {
        reclamations.add(r);
        FileManager.saveComplaints(reclamations);
    }

    // Getters and Setters
    public TreeSet<Personne> getVoyageurs() {
        return voyageurs;
    }

    public TreeSet<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(TreeSet<Ticket> tickets) {
        this.tickets = tickets != null ? tickets : new TreeSet<>();
        FileManager.saveFareMedia(this.tickets, this.navigationCards);
    }

    public TreeSet<NavigationCard> getNavigationCards() {
        return navigationCards;
    }

    public void setNavigationCards(TreeSet<NavigationCard> navigationCards) {
        this.navigationCards = navigationCards != null ? navigationCards : new TreeSet<>();
        FileManager.saveFareMedia(this.tickets, this.navigationCards);
    }

    public TreeSet<MoyenTransport> getMoyens() {
        return moyens;
    }

    public void setMoyens(TreeSet<MoyenTransport> moyens) {
        this.moyens = moyens != null ? moyens : new TreeSet<>();
        FileManager.saveSuspendables(this.stations, this.moyens);
    }

    public TreeSet<Station> getStations() {
        return stations;
    }

    public void setStations(TreeSet<Station> stations) {
        this.stations = stations != null ? stations : new TreeSet<>();
        FileManager.saveSuspendables(this.stations, this.moyens);
    }

    public TreeSet<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(TreeSet<Reclamation> reclamations) {
        this.reclamations = reclamations != null ? reclamations : new TreeSet<>();
        FileManager.saveComplaints(this.reclamations);
    }
}
