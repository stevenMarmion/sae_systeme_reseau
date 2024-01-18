package src_test;

import java.time.LocalDate;

import src_class.src_class_modele.Message;

public class MessageTest {

    public static void main(String[] args) {
        testCreationMessage();
        testIncrementLike();
        testEcritureMessage();
    }

    private static void testCreationMessage() {
        String contenu = "Contenu du message";
        String nomExpediteur = "Expéditeur";
        int nombreLike = 5;

        // Test de création de message
        Message message = new Message(contenu, nomExpediteur, nombreLike);
        assert contenu.equals(message.getContenu()) : "Erreur lors du test de création de message (contenu)";
        assert nomExpediteur.equals(message.getNomExpediteur()) : "Erreur lors du test de création de message (nomExpediteur)";
        assert nombreLike == message.getNombreLike() : "Erreur lors du test de création de message (nombreLike)";
        assert message.getDate().equals(java.sql.Date.valueOf(LocalDate.now())) : "Erreur lors du test de création de message (date)";
        System.out.println("Test de création de message réussi");
    }

    private static void testIncrementLike() {
        Message message = new Message("Contenu", "Expéditeur", 2);

        // Test d'incrémentation de like
        message.incrementLike();
        assert message.getNombreLike() == 3 : "Erreur lors du test d'incrémentation de like";
        System.out.println("Test d'incrémentation de like réussi");
    }

    private static void testEcritureMessage() {
        Message message = new Message("Contenu", "Expéditeur", 2);
        message.setId(1);

        // Test d'écriture de message
        String expectedOutput = "message : \n contenu : 'Contenu' \n expediteur : 'Expéditeur' \n date : '2022-01-18' \n nombreLike : '2' \n id : '1'";
        assert expectedOutput.equals(message.ecritureMessage()) : "Erreur lors du test d'écriture de message";
        System.out.println("Test d'écriture de message réussi");
    }
}

