package src_test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import src_exception.ExceptionCommandesAlreadyAdd;
import src_exception.ExceptionIpAlreadyDefined;
import src_exception.ExceptionIpEmpty;
import src_class.Server;
import src_class.src_class_commande.Commande;
import src_class.src_class_commande.CommandeDeleteServer;
import src_class.src_class_commande.CommandeFollow;
import src_class.src_class_commande.CommandeRemove;

public class ServerTest {

    public static void main(String[] args) {
        try {
            testCreationServer();
            testAjouteNouvelleCommandeServer();
            testAjouteNouvelleCommandeClient();
            testAjouteListeCommandes();
            testVerifieIP();
            // Ajoutez d'autres tests au besoin
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testCreationServer() throws ClassNotFoundException, SQLException {
        InetAddress ip = InetAddress.getLoopbackAddress();
        ArrayList<Commande> commandesServer = new ArrayList<>();
        ArrayList<Commande> commandesClient = new ArrayList<>();
        
        // Test de création du serveur
        Server server = new Server(ip, commandesServer, commandesClient);
        assert ip.equals(server.getIpServer()) : "Erreur lors du test de création du serveur (IP)";
        assert commandesServer.equals(server.getCommandesServer()) : "Erreur lors du test de création du serveur (commandesServer)";
        assert commandesClient.equals(server.getCommandesClient()) : "Erreur lors du test de création du serveur (commandesClient)";
        System.out.println("Test de création du serveur réussi");
    }

    private static void testAjouteNouvelleCommandeServer() throws ClassNotFoundException, SQLException {
        Server server = new Server(InetAddress.getLoopbackAddress());
        Commande commandeDeleteServer = new CommandeDeleteServer(server);

        // Test ajout de nouvelle commande server
        try {
            assert server.ajouteNouvelleCommandesServer(commandeDeleteServer) : "Erreur lors du test d'ajout de nouvelle commande server";
            assert server.getCommandesServer().contains(commandeDeleteServer) : "Erreur lors du test d'ajout de nouvelle commande server (liste commandes server)";
            System.out.println("Test d'ajout de nouvelle commande server réussi");

            // Test exception
            server.ajouteNouvelleCommandesServer(commandeDeleteServer); // Devrait lancer une exception
            System.out.println("Erreur : Le test d'ajout de nouvelle commande server n'a pas lancé d'exception");
        } catch (ExceptionCommandesAlreadyAdd exception) {
            System.out.println("Test d'ajout de nouvelle commande server réussi (exception)");
        }
    }

    private static void testAjouteNouvelleCommandeClient() throws ClassNotFoundException, SQLException {
        Server server = new Server(InetAddress.getLoopbackAddress());
        Commande commandeFollow = new CommandeFollow(server);

        // Test ajout de nouvelle commande client
        try {
            assert server.ajouteNouvelleCommandesClient(commandeFollow) : "Erreur lors du test d'ajout de nouvelle commande client";
            assert server.getCommandesClient().contains(commandeFollow) : "Erreur lors du test d'ajout de nouvelle commande client (liste commandes client)";
            System.out.println("Test d'ajout de nouvelle commande client réussi");

            // Test exception
            server.ajouteNouvelleCommandesClient(commandeFollow); // Devrait lancer une exception
            System.out.println("Erreur : Le test d'ajout de nouvelle commande client n'a pas lancé d'exception");
        } catch (ExceptionCommandesAlreadyAdd exception) {
            System.out.println("Test d'ajout de nouvelle commande client réussi (exception)");
        }
    }

    private static void testAjouteListeCommandes() throws ClassNotFoundException, SQLException, ExceptionCommandesAlreadyAdd {
        Server server = new Server(InetAddress.getLoopbackAddress());
        Commande commandeRemove = new CommandeRemove(server);

        // Test ajout de liste de commandes
        ArrayList<Commande> listeCommandes = new ArrayList<>();
        listeCommandes.add(commandeRemove);

        assert server.ajouteListCommandes(listeCommandes) : "Erreur lors du test d'ajout de liste de commandes";
        assert server.getCommandesServer().contains(commandeRemove) : "Erreur lors du test d'ajout de liste de commandes (liste commandes server)";
        System.out.println("Test d'ajout de liste de commandes réussi");
    }

    private static void testVerifieIP() throws ExceptionIpAlreadyDefined, ExceptionIpEmpty, ClassNotFoundException, SQLException, UnknownHostException {
        Server server = new Server(InetAddress.getLoopbackAddress());
        InetAddress newIP = InetAddress.getByName("192.168.1.1");

        // Test vérification IP
        assert server.verifieIP(newIP) : "Erreur lors du test de vérification d'IP";
        assert server.setIpServer(newIP) : "Erreur lors du test de changement d'IP";
        System.out.println("Test de vérification d'IP réussi");
    }
}

