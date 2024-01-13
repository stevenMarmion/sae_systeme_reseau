package src_interface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Profile extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setFullScreen(true);

        BorderPane page = new BorderPane();

        // Nom de l'utilisateur
        Label usernameLabel = new Label("Nom de l'Utilisateur");
        usernameLabel.setStyle("-fx-font-size: 30; -fx-font-weight: bold;");

        // Nombre d'abonnés et d'abonnements
        Label followersLabel = new Label("Abonnés: 100");
        followersLabel.setFont(new Font(20));
        Label followingLabel = new Label("Abonnements: 50");
        followingLabel.setFont(new Font(20));

        HBox userStatsBox = new HBox(20, followersLabel, followingLabel);
        userStatsBox.setAlignment(Pos.CENTER);

        VBox userInfoBox = new VBox(10, usernameLabel, userStatsBox);
        userInfoBox.setAlignment(Pos.CENTER);
        userInfoBox.setPadding(new Insets(10));

        // Liste des posts de l'utilisateur
        VBox postsBox = createPostsBox();

        // Barre de navigation (en bas)
        HBox navigationBar = createNavigationBar();

        // Ajout des parties supérieure et inférieure à la page de profil
        page.setTop(userInfoBox);
        page.setCenter(postsBox);
        page.setBottom(navigationBar);

        Scene scene = new Scene(page);

        this.primaryStage.setScene(scene);
    }

    private VBox createPostsBox() {
        VBox postsBox = new VBox(10);
        postsBox.setAlignment(Pos.CENTER);
        postsBox.setPadding(new Insets(10, 50, 10, 50));
        postsBox.setStyle("-fx-background-color: #1F1F2A;");

        // Ajout de posts à titre d'exemple (utilisez une boucle appropriée pour 200 posts)
        for (int i = 1; i <= 200; i++) {
            Label postLabel = new Label("Post #" + i);
            postLabel.setFont(new Font(20));
            postLabel.setStyle("-fx-background-color: #1F1F2A;");
            postsBox.getChildren().add(postLabel);
        }

        // ScrollPane pour rendre la liste des posts scrollable
        ScrollPane scrollPane = new ScrollPane(postsBox);
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
        searchButton.setOnAction(e -> showSearchPage());

        // Bouton Profil (déjà sur la page de profil, pas de changement)
        Button profileButton = new Button("Profil");
        profileButton.setFont(new Font(20));

        navigationBar.getChildren().addAll(homeButton, searchButton, profileButton);
        navigationBar.setPadding(new Insets(0, 0, 50, 0));
        return navigationBar;
    }

    private void showHomePage() {
        // Accueil homePage = new Accueil(primaryStage);
        // Scene homeScene = new Scene(homePage);
        // applyDarkTheme(homeScene);

        // primaryStage.setScene(homeScene);
        // primaryStage.setFullScreen(true);
    }

    private void showSearchPage() {
        // Recherche searchPage = new Recherche(primaryStage);
        // Scene searchScene = new Scene(searchPage);
        // applyDarkTheme(searchScene);

        // primaryStage.setScene(searchScene);
        // primaryStage.setFullScreen(true);
    }

    // private void applyDarkTheme(Scene scene) {
    //     java.net.URL resourceUrl = Profile.class.getResource("./darkTheme.css");
    //     if (resourceUrl != null) {
    //         String externalForm = resourceUrl.toExternalForm();
    //         scene.getStylesheets().add(externalForm);
    //     } else {
    //         System.out.println("La ressource n'a pas été trouvée.");
    //     }
    // }
}
