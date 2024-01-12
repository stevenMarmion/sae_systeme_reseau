package src_interface;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ma Première Application JavaFX");

        // Créer un bouton
        Button bouton = new Button("Cliquez-moi !");
        
        // Définir une action lors du clic sur le bouton
        bouton.setOnAction(e -> {
            System.out.println("Bonjour, vous avez cliqué sur le bouton !");
        });

        // Créer une mise en page et ajouter le bouton
        StackPane layout = new StackPane();
        layout.getChildren().add(bouton);

        // Créer la scène
        Scene scene = new Scene(layout, 300, 250);

        // Définir la scène principale
        primaryStage.setScene(scene);

        // Afficher la fenêtre
        primaryStage.show();
    }
}

