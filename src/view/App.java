package view;

import controller.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
import javafx.stage.Stage;
import model.Service;
import model.FileManager;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize Service singleton to load data
        Service.getInstance();

        // Main container with scrolling
        VBox root = new VBox(40);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(40));
        root.getStyleClass().addAll("root", "main-container");

        // Welcome Section
        VBox welcomeSection = new VBox(15);
        welcomeSection.setAlignment(Pos.CENTER);
        welcomeSection.getStyleClass().add("welcome-section");
        
        Label welcomeLabel = new Label("Welcome to ESIRUN");
        welcomeLabel.getStyleClass().add("welcome-header");
        
        Label subtitleLabel = new Label("Your Complete Transport Management Solution");
        subtitleLabel.getStyleClass().add("welcome-subtitle");
        
        welcomeSection.getChildren().addAll(welcomeLabel, subtitleLabel);

        // Platform Header
        StackPane headerContainer = new StackPane();
        headerContainer.getStyleClass().add("header-container");
        headerContainer.setPadding(new Insets(30, 0, 30, 0));
        
        Label headerLabel = new Label("Management Platform");
        headerLabel.getStyleClass().add("main-header");
        headerContainer.getChildren().add(headerLabel);

        // Display Section
        VBox displaySection = createSection("Display Functions", "display-section");
        HBox displayContent = new HBox(30);
        displayContent.setAlignment(Pos.CENTER);
        displayContent.getStyleClass().add("responsive-container");
        
        VBox displayLeftColumn = new VBox(20);
        VBox displayRightColumn = new VBox(20);
        displayLeftColumn.setAlignment(Pos.CENTER);
        displayRightColumn.setAlignment(Pos.CENTER);
        displayLeftColumn.getStyleClass().add("button-column");
        displayRightColumn.getStyleClass().add("button-column");

        Button displayUsersBtn = createMenuButton("Display Users", "display-button");
        displayUsersBtn.setOnAction(e -> openWindow(new DisplayUsersView()));

        Button displayFareMediaBtn = createMenuButton("Display Fare Media", "display-button");
        displayFareMediaBtn.setOnAction(new DisplayFareMedia());

        Button displayStationsBtn = createMenuButton("Display Stations & Transport", "display-button");
        displayStationsBtn.setOnAction(e -> openWindow(new DisplayStationsAndMoyensView()));

        Button displayComplaintsBtn = createMenuButton("Display Complaints", "display-button");
        displayComplaintsBtn.setOnAction(new DisplayComplaints());

        displayLeftColumn.getChildren().addAll(displayUsersBtn, displayStationsBtn);
        displayRightColumn.getChildren().addAll(displayFareMediaBtn, displayComplaintsBtn);
        displayContent.getChildren().addAll(displayLeftColumn, displayRightColumn);
        displaySection.getChildren().add(displayContent);

        // Add Section
        VBox addSection = createSection("Add New Data", "add-section");
        HBox addContent = new HBox(30);
        addContent.setAlignment(Pos.CENTER);
        addContent.getStyleClass().add("responsive-container");
        
        VBox addLeftColumn = new VBox(20);
        VBox addRightColumn = new VBox(20);
        addLeftColumn.setAlignment(Pos.CENTER);
        addRightColumn.setAlignment(Pos.CENTER);
        addLeftColumn.getStyleClass().add("button-column");
        addRightColumn.getStyleClass().add("button-column");

        Button addUserBtn = createMenuButton("Add User", "add-button");
        addUserBtn.setOnAction(e -> openWindow(new AddUserView()));

        Button addFareMediaBtn = createMenuButton("Add Fare Media", "add-button");
        addFareMediaBtn.setOnAction(e -> openWindow(new AddFareMediaView()));

        Button addStationBtn = createMenuButton("Add Station/Transport", "add-button");
        addStationBtn.setOnAction(e -> openWindow(new AddStationView()));

        Button registerComplaintBtn = createMenuButton("Register Complaint", "add-button");
        registerComplaintBtn.setOnAction(e -> openWindow(new RegisterComplaintView()));

        addLeftColumn.getChildren().addAll(addUserBtn, addStationBtn);
        addRightColumn.getChildren().addAll(addFareMediaBtn, registerComplaintBtn);
        addContent.getChildren().addAll(addLeftColumn, addRightColumn);
        addSection.getChildren().add(addContent);

        // Validation Section
        VBox validationSection = createSection("Validation Functions", "validation-section");
        validationSection.getStyleClass().add("validation-container");
        
        Button validateFareMediaBtn = createMenuButton("Validate Transport Title", "validate-button");
        validateFareMediaBtn.setOnAction(e -> openWindow(new ValidateFareMediaView()));
        
        validationSection.getChildren().add(validateFareMediaBtn);

        // Add all sections to root
        root.getChildren().addAll(welcomeSection, headerContainer, displaySection, addSection, validationSection);

        // Create a ScrollPane and add the root container to it
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        
        // Set the ScrollPane to show vertical scrollbar as needed
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        // Hide horizontal scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        // Create scene with the ScrollPane as the root
        Scene scene = new Scene(scrollPane,600,600);

        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        // Set up window close handler to ensure data is saved
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            Service service = Service.getInstance();
            // Save all data one final time
            FileManager.saveUsers(service.getVoyageurs());
            FileManager.saveFareMedia(service.getTickets(), service.getNavigationCards());
            FileManager.saveComplaints(service.getReclamations());
            FileManager.saveSuspendables(service.getStations(), service.getMoyens());
            Platform.exit();
        });

        primaryStage.setTitle("ESIRUN Management Platform");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createSection(String title, String styleClass) {
        VBox section = new VBox(25);
        section.setAlignment(Pos.CENTER);
        section.getStyleClass().addAll("section-container", styleClass);
        section.setPadding(new Insets(30));
        
        Label sectionLabel = new Label(title);
        sectionLabel.getStyleClass().add("section-header");
        
        section.getChildren().add(sectionLabel);
        return section;
    }

    private Button createMenuButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().addAll("menu-button", styleClass);
        return button;
    }

    private void openWindow(Object view) {
        Stage stage = new Stage();
        if (view instanceof DisplayUsersView) {
            ((DisplayUsersView) view).start(stage);
        } else if (view instanceof AddUserView) {
            ((AddUserView) view).start(stage);
        } else if (view instanceof AddFareMediaView) {
            ((AddFareMediaView) view).start(stage);
        } else if (view instanceof AddStationView) {
            ((AddStationView) view).start(stage);
        } else if (view instanceof RegisterComplaintView) {
            ((RegisterComplaintView) view).start(stage);
        } else if (view instanceof DisplayStationsAndMoyensView) {
            ((DisplayStationsAndMoyensView) view).start(stage);
        } else if (view instanceof ValidateFareMediaView) {
            ((ValidateFareMediaView) view).start(stage);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}