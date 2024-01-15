package src_interface;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ConnexionInscription extends Application {

    private Stage primaryStage;
    private Scene loginScene;
    private Scene registrationScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setFullScreen(true);
        primaryStage.setTitle("ReseauIuto");  // Titre de l'application

        // Création d'un formulaire de connexion
        GridPane loginGrid = createLoginForm();

        // Création d'un formulaire d'inscription
        GridPane registrationGrid = createRegistrationForm();

        loginScene = new Scene(loginGrid);
        applyDarkTheme(loginScene);

        primaryStage.setScene(loginScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private GridPane createLoginForm() {
        GridPane grid = createForm("Login");

        Hyperlink registerLink = new Hyperlink("Se créer un compte ?");
        registerLink.setFont(new Font(15));
        registerLink.setOnAction(e -> showRegistrationForm());

        HBox linkBox = new HBox(registerLink);
        linkBox.setAlignment(Pos.CENTER_RIGHT);

        grid.add(linkBox, 1, 5);

        return grid;
    }

    private GridPane createRegistrationForm() {
        GridPane grid = createForm("Registration");

        Hyperlink loginLink = new Hyperlink("Déjà un compte ? Se connecter.");
        loginLink.setFont(new Font(15));
        loginLink.setOnAction(e -> showLoginForm());

        HBox linkBox = new HBox(loginLink);
        linkBox.setAlignment(Pos.CENTER_RIGHT);

        grid.add(linkBox, 1, 5);

        return grid;
    }

    private GridPane createForm(String formTitle) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label titleLabel = new Label("ReseauIuto");  // Affichage du titre seulement
        titleLabel.setStyle("-fx-font-size: 60; -fx-font-weight: bold; -fx-text-fill: white;");  // Style du titre
        titleLabel.setPadding(new Insets(0, 0, 150, 0));
        grid.add(titleLabel, 0, 0, 2, 1);

        Label userNameLabel = new Label("Username:");
        userNameLabel.setFont(new Font(15));
        grid.add(userNameLabel, 0, 1);

        TextField userNameTextField = new TextField();
        userNameLabel.setFont(new Font(15));
        grid.add(userNameTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(new Font(15));
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        passwordField.setFont(new Font(15));
        grid.add(passwordField, 1, 2);

        Button actionButton = new Button(formTitle);
        actionButton.setFont(new Font(15));
        actionButton.setOnAction(e -> showHomePage());
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.getChildren().addAll(actionButton);

        grid.add(buttonBox, 1, 4);

        return grid;
    }

    private void showRegistrationForm() {
        if (registrationScene == null) {
            GridPane registrationGrid = createRegistrationForm();
            registrationScene = new Scene(registrationGrid);
            applyDarkTheme(registrationScene);
        }
        primaryStage.setScene(registrationScene);
        primaryStage.setFullScreen(true);
    }

    private void showLoginForm() {
        primaryStage.setScene(loginScene);
        primaryStage.setFullScreen(true);
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

    private void showHomePage() {
        // Logique pour afficher la page d'accueil
        Accueil homePage = new Accueil(primaryStage);
        Scene homeScene = new Scene(homePage);
        applyDarkTheme(homeScene);

        primaryStage.setScene(homeScene);
        primaryStage.setFullScreen(true);
    }
}
