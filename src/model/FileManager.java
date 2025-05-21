package model;

import java.io.*;
import java.util.TreeSet;

public class FileManager {
    private static final String USERS_FILE = "users.dat";
    private static final String FARE_MEDIA_FILE = "faremedium.dat";
    private static final String COMPLAINTS_FILE = "complaints.dat";
    private static final String SUSPENDABLES_FILE = "suspendables.dat";

    // Save methods
    public static void saveUsers(TreeSet<Personne> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    public static void saveFareMedia(TreeSet<Ticket> tickets, TreeSet<NavigationCard> cards) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FARE_MEDIA_FILE))) {
            oos.writeObject(tickets);
            oos.writeObject(cards);
        } catch (IOException e) {
            System.err.println("Error saving fare media: " + e.getMessage());
        }
    }

    public static void saveComplaints(TreeSet<Reclamation> complaints) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COMPLAINTS_FILE))) {
            oos.writeObject(complaints);
        } catch (IOException e) {
            System.err.println("Error saving complaints: " + e.getMessage());
        }
    }

    public static void saveSuspendables(TreeSet<Station> stations, TreeSet<MoyenTransport> moyens) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SUSPENDABLES_FILE))) {
            oos.writeObject(stations);
            oos.writeObject(moyens);
        } catch (IOException e) {
            System.err.println("Error saving suspendables: " + e.getMessage());
        }
    }

    // Load methods
    @SuppressWarnings("unchecked")
    public static TreeSet<Personne> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            return (TreeSet<Personne>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
            return new TreeSet<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadFareMedia(Service service) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FARE_MEDIA_FILE))) {
            TreeSet<Ticket> tickets = (TreeSet<Ticket>) ois.readObject();
            TreeSet<NavigationCard> cards = (TreeSet<NavigationCard>) ois.readObject();
            service.setTickets(tickets);
            service.setNavigationCards(cards);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading fare media: " + e.getMessage());
            service.setTickets(new TreeSet<>());
            service.setNavigationCards(new TreeSet<>());
        }
    }

    @SuppressWarnings("unchecked")
    public static TreeSet<Reclamation> loadComplaints() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COMPLAINTS_FILE))) {
            return (TreeSet<Reclamation>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading complaints: " + e.getMessage());
            return new TreeSet<>();
        }
    }

    @SuppressWarnings("unchecked")
    public static void loadSuspendables(Service service) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SUSPENDABLES_FILE))) {
            TreeSet<Station> stations = (TreeSet<Station>) ois.readObject();
            TreeSet<MoyenTransport> moyens = (TreeSet<MoyenTransport>) ois.readObject();
            service.setStations(stations);
            service.setMoyens(moyens);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading suspendables: " + e.getMessage());
            service.setStations(new TreeSet<>());
            service.setMoyens(new TreeSet<>());
        }
    }
} 