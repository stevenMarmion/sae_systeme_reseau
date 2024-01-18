package src_interface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Accueil extends BorderPane {

    Stage stage;

    public Accueil(Stage primaryStage) {
        stage = primaryStage;

        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #1F1F2A;");  // Fond bleu foncé

        // En-tête de la page (Nom de profil et bouton pour les messages privés)
        HBox headerBox = createHeaderBox();
        borderPane.setTop(headerBox);

        // Contenu de la page (Messages)
        VBox messageBox = createMessageBox();
        borderPane.setCenter(messageBox);

        HBox navigationBar = createNavigationBar();  // Nouvelle méthode ajoutée
        borderPane.setBottom(navigationBar);

        // Création de la scène
        Scene scene = new Scene(borderPane);
        applyDarkTheme(scene);

        // Affichage de la scène
        setTop(createHeaderBox());
        setCenter(createMessageBox());
        setBottom(createNavigationBar());
    }

    private HBox createHeaderBox() {
        HBox headerBox = new HBox(stage.getWidth()/3);
    
        // Bouton pour les messages privés
        Button privateMessagesButton = new Button("Messages privés");
        privateMessagesButton.setFont(new Font(20));
        privateMessagesButton.setOnAction(e -> showPrivateMessages());
    
        // Nom de profil (à titre d'exemple, tu devrais récupérer le nom de profil de l'utilisateur connecté)
        Label profileNameLabel = new Label("Nom de Profil");
        profileNameLabel.setPadding(new Insets(0, 0, 0, 15));
        profileNameLabel.setFont(new Font(20));

        Label titleHomePage = new Label("ReseauIuto");
        titleHomePage.setStyle("-fx-font-size: 40; -fx-font-weight: bold; -fx-text-fill: white;"); 
        titleHomePage.setFont(new Font(20));
    
        headerBox.getChildren().addAll(profileNameLabel, titleHomePage, privateMessagesButton);
        headerBox.setPadding(new Insets(10, 0, 60, 0));
        return headerBox;
    }
    
    private VBox createMessageBox() {
        VBox messageBox = new VBox(10);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setPadding(new Insets(10, 50, 10, 50));  // Espacements ajustés
    
        // Ajout de messages à titre d'exemple
        for (int i = 1; i <= 200; i++) {
            Label messageLabel = new Label("Message #" + i);
            messageLabel.setFont(new Font(20));
            messageBox.setStyle("-fx-background-color: #1F1F2A;");
            messageBox.getChildren().add(messageLabel);
        }
    
        ScrollPane scrollPane = new ScrollPane(messageBox);
        scrollPane.setStyle("-fx-background-color: #1F1F2A;");
        scrollPane.getStyleClass().add("scroll-pane");
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    
        VBox containerBox = new VBox(scrollPane);
        containerBox.setPadding(new Insets(10));
    
        return containerBox;
    }

    private void applyDarkTheme(Scene scene) {
        java.net.URL resourceUrl = ConnexionInscription.class.getResource("./src_interface_style/darkTheme.css");
        if (resourceUrl != null) {
            String externalForm = resourceUrl.toExternalForm();
            scene.getStylesheets().add(externalForm);
        } else {
            System.out.println("La ressource n'a pas été trouvée.");
        }
    }
    private void showPrivateMessages() {
        // Logique pour afficher les messages privés
        System.out.println("Afficher les messages privés");
    }

    private HBox createNavigationBar() {
        HBox navigationBar = new HBox(10);
        navigationBar.setAlignment(Pos.CENTER);
        navigationBar.setPadding(new Insets(10));
    
        // Bouton Accueil (maison)
        Button homeButton = new Button("Accueil");
        homeButton.setFont(new Font(20));
        homeButton.setOnAction(e -> refreshPage());
    
        // Bouton Recherche (loupe)
        Button searchButton = new Button("Recherche");
        searchButton.setFont(new Font(20));
        searchButton.setOnAction(e -> showSearchPage());
    
        // Bouton Profil
        Button profileButton = new Button("Profil");
        profileButton.setFont(new Font(20));
        profileButton.setOnAction(e -> showProfilePage());
    
        navigationBar.getChildren().addAll(homeButton, searchButton, profileButton);
        navigationBar.setPadding(new Insets(0, 0, 50, 0));
        return navigationBar;
    }
    
    private void refreshPage() {
        // Logique pour rafraîchir la page
        System.out.println("Rafraîchir la page");
    }
    
    private void showSearchPage() {
        Recherche searchPage = new Recherche(stage);
        Scene searchScene = new Scene(searchPage);
        applyDarkTheme(searchScene);

        stage.setScene(searchScene);
        stage.setFullScreen(true);
    }
    
    private void showProfilePage() {
        Profil profilePage = new Profil(stage);
        Scene profileScene = new Scene(profilePage);
        applyDarkTheme(profileScene);
    
        stage.setScene(profileScene);
        stage.setFullScreen(true);
    }
}
