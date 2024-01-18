package src_interface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Recherche extends BorderPane {

    Stage primaryStage;

    public Recherche(Stage stage) {
        primaryStage = stage;
        this.primaryStage.setFullScreen(true);

        // Barre de recherche
        TextField searchField = new TextField();
        searchField.setFont(new Font(20));
        searchField.setPromptText("Rechercher...");
        searchField.setPrefWidth(stage.getWidth()/2);

        HBox searchBox = new HBox(searchField);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setPadding(new Insets(10));

        // Liste de messages (au centre)
        VBox messageBox = createMessageBox();  // Méthode similaire à celle de la page d'accueil

        // Barre de navigation (en bas)
        HBox navigationBar = createNavigationBar();

        // Ajout des éléments à la page de recherche
        setTop(searchBox);
        setCenter(messageBox);
        setBottom(navigationBar);
    }

    private VBox createMessageBox() {
        VBox messageBox = new VBox(10);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setPadding(new Insets(10, 50, 10, 50));
        messageBox.setStyle("-fx-background-color: #1F1F2A;");

        // Ajout de messages à titre d'exemple
        for (int i = 1; i <= 200; i++) {
            Button messageButton = new Button("Message #" + i);
            messageButton.setFont(new Font(20));
            messageButton.setStyle("-fx-background-color: #1F1F2A;");
            messageBox.getChildren().add(messageButton);
        }

        // ScrollPane pour rendre la liste des posts scrollable
        ScrollPane scrollPane = new ScrollPane(messageBox);
        scrollPane.setStyle("-fx-background-color: #1F1F2A;");
        scrollPane.setFitToWidth(true);
        scrollPane.setVmax(Double.MAX_VALUE);

        VBox postsBoxReturn = new VBox(scrollPane);
        postsBoxReturn.setPadding(new Insets(10));

        return postsBoxReturn;
    }

    private HBox createNavigationBar() {
        HBox navigationBar = new HBox(10);
        navigationBar.setAlignment(Pos.CENTER);
        navigationBar.setPadding(new Insets(10));

        // Bouton Accueil
        Button homeButton = new Button("Accueil");
        homeButton.setFont(new Font(20));
        homeButton.setOnAction(e -> showHomePage());

        // Bouton Recherche
        Button searchButton = new Button("Recherche");
        searchButton.setFont(new Font(20));
        // On reste sur la page de recherche, pas de logique associée ici

        // Bouton Profil
        Button profileButton = new Button("Profil");
        profileButton.setFont(new Font(20));
        profileButton.setOnAction(e -> showProfilePage());

        navigationBar.getChildren().addAll(homeButton, searchButton, profileButton);
        navigationBar.setPadding(new Insets(0, 0, 50, 0));
        return navigationBar;
    }

    private void showHomePage() {
        Accueil homePage = new Accueil(primaryStage);
        Scene homeScene = new Scene(homePage);
        applyDarkTheme(homeScene);

        primaryStage.setScene(homeScene);
        primaryStage.setFullScreen(true);
    }

    private void showProfilePage() {
        Profil profilePage = new Profil(primaryStage);
        Scene profileScene = new Scene(profilePage);
        applyDarkTheme(profileScene);
    
        primaryStage.setScene(profileScene);
        primaryStage.setFullScreen(true);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("ReseauIuto - Recherche");

        Recherche searchPage = new Recherche(primaryStage);
        Scene scene = new Scene(searchPage, 800, 600);
        applyDarkTheme(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
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
}
