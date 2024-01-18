package src_test;

import java.net.InetAddress;
import java.util.ArrayList;

import src_class.src_class_modele.Client;
import src_class.src_class_modele.Message;
import src_exception.ExceptionFollowUser;

public class ClientTest {

    public static void main(String[] args) throws ExceptionFollowUser {
        testCreationClient();
        testAjouteAbonnement();
    }

    private static void testCreationClient() {
        InetAddress ip = InetAddress.getLoopbackAddress();
        String username = "TestUser";
        Client client = new Client(ip, username);

        // Vérifier que les propriétés sont correctement initialisées
        assert ip.equals(client.getIp()) : "Erreur lors du test de création du client (IP)";
        assert username.equals(client.getUsername()) : "Erreur lors du test de création du client (username)";
        assert new ArrayList<Client>().equals(client.getAbonnement()) : "Erreur lors du test de création du client (abonnement)";
        assert new ArrayList<Client>().equals(client.getAbonnes()) : "Erreur lors du test de création du client (abonnes)";
        assert new ArrayList<Message>().equals(client.getMessages()) : "Erreur lors du test de création du client (messages)";
        assert null == client.getServer() : "Erreur lors du test de création du client (serveur)";
        System.out.println("Test de création du client réussi");
    }

    private static void testAjouteAbonnement() throws ExceptionFollowUser {
        Client client = new Client(InetAddress.getLoopbackAddress(), "User1");
        Client abonnement = new Client(InetAddress.getLoopbackAddress(), "User2");

        // Test ajout d'abonnement
        try {
            assert client.ajouteAbonnement(abonnement) : "Erreur lors du test d'ajout d'abonnement";
            assert client.getAbonnement().contains(abonnement) : "Erreur lors du test d'ajout d'abonnement (liste d'abonnements)";
            System.out.println("Test d'ajout d'abonnement réussi");

            // Test exception
            client.ajouteAbonnement(abonnement); // Devrait lancer une exception
            System.out.println("Erreur : Le test d'ajout d'abonnement n'a pas lancé d'exception");
        } catch (ExceptionFollowUser exception) {
            System.out.println("Test d'ajout d'abonnement réussi (exception)");
            throw new ExceptionFollowUser(client);
        }
    }
}
