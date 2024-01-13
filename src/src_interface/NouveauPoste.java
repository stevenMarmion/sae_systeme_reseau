package src_interface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class NouveauPoste extends VBox {

    Stage stage ;

    public NouveauPoste(Stage primaryStage) {
        stage = primaryStage;

        setPadding(new Insets(10));
        setSpacing(10);

        Label titlePost = new Label("Poster un nouveau message pour vos abonnés !");
        titlePost.setStyle("-fx-font-size: 40; -fx-font-weight: bold; -fx-text-fill: white;"); 
        titlePost.setFont(new Font(20));

        TextArea postTextArea = new TextArea();
        postTextArea.setPromptText("Écrivez votre nouveau post ici...");
        postTextArea.setFont(new Font(20));

        Button postButton = new Button("Poster");
        postButton.setFont(new Font(20));
        postButton.setOnAction(e -> {
            // Logique pour poster le nouveau post
            String postContent = postTextArea.getText();
            System.out.println("Nouveau post: " + postContent);
        });

        Label label = new Label("Ce poste sera vu par tout vos abonnés ...");
        label.setStyle("-fx-font-style: italic; -fx-text-fill: white; -fx-font-size: 20;");
        label.setPadding(new Insets(50, 0, 0, 0));

        getChildren().addAll(titlePost, label, postTextArea, postButton, createNavigationBar());
        this.setPadding(new Insets(50, 300, 0, 300));
    }

    private HBox createNavigationBar() {
        HBox navigationBar = new HBox(10);
        navigationBar.setAlignment(Pos.CENTER);
        navigationBar.setPadding(new Insets(10));
    
        // Bouton Accueil (maison)
        Button homeButton = new Button("Accueil");
        homeButton.setFont(new Font(20));
        homeButton.setOnAction(e -> showHomePage());
    
        // Bouton Recherche (loupe)
        Button searchButton = new Button("Recherche");
        searchButton.setFont(new Font(20));
        searchButton.setOnAction(e -> showSearchPage());
    
        // Bouton Profil
        Button profileButton = new Button("Profil");
        profileButton.setFont(new Font(20));
        profileButton.setOnAction(e -> showProfilePage());

        // Bouton "Plus" pour écrire un nouveau post
        Button newPostButton = new Button("+");
        newPostButton.setFont(new Font(20));
        newPostButton.setOnAction(e -> showNewPostPage());
    
        navigationBar.getChildren().addAll(homeButton, searchButton, newPostButton, profileButton);
        navigationBar.setPadding(new Insets(0, 0, 50, 0));
        return navigationBar;
    }

    private void showHomePage() {
        Accueil homePage = new Accueil(stage);
        Scene homeScene = new Scene(homePage);
        applyDarkTheme(homeScene);

        stage.setScene(homeScene);
        stage.setFullScreen(true);
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

    private void showNewPostPage() {
        NouveauPoste newPostPage = new NouveauPoste(stage);
        Scene newPostScene = new Scene(newPostPage);
        applyDarkTheme(newPostScene);
    
        stage.setScene(newPostScene);
        stage.setFullScreen(true);
    }

    private void applyDarkTheme(Scene scene) {
        java.net.URL resourceUrl = ConnexionInscription.class.getResource("./darkTheme.css");
        if (resourceUrl != null) {
            String externalForm = resourceUrl.toExternalForm();
            scene.getStylesheets().add(externalForm);
        } else {
            System.out.println("La ressource n'a pas été trouvée.");
        }
    }
}


