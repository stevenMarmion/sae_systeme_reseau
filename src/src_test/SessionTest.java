package src_test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import src_class.Server;
import src_class.Session;
import src_class.src_class_modele.Message;

public class SessionTest {

    public static void main(String[] args) {
        try {
            testSessionCreation();
            testReconstitueMessage();
            testUserExistant();
            // Ajoutez d'autres tests au besoin
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testSessionCreation() throws ClassNotFoundException, SQLException {
        InetAddress ip = InetAddress.getLoopbackAddress();
        Server server = new Server(ip);
        Socket socket = new Socket();
        Session session = new Session(server, socket, ip);

        // Test de création de session
        assert session.getSocket().equals(socket) : "Erreur lors du test de création de session (Socket)";
        assert session.getServer().equals(server) : "Erreur lors du test de création de session (Server)";
        System.out.println("Test de création de session réussi");
    }

    private static void testReconstitueMessage() {
        Session session = new Session(null, null, null);

        // Test reconstitution de message
        String messageSrc = "Hello,John,2022-01-20,5,123";
        Message messageReconsitue = session.reconstitueMessage(messageSrc);

        assert messageReconsitue.getContenu().equals("Hello") : "Erreur lors du test de reconstitution de message (contenu)";
        assert messageReconsitue.getNomExpediteur().equals("John") : "Erreur lors du test de reconstitution de message (nomExpediteur)";
        assert messageReconsitue.getNombreLike() == 5 : "Erreur lors du test de reconstitution de message (nombreLike)";
        assert messageReconsitue.getId() == 123 : "Erreur lors du test de reconstitution de message (id)";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date expectedDate = formatter.parse("2022-01-20");
            assert messageReconsitue.getDate().equals(new java.sql.Date(expectedDate.getTime())) : "Erreur lors du test de reconstitution de message (date)";
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Test de reconstitution de message réussi");
    }

    private static void testUserExistant() throws UnknownHostException, SQLException, ClassNotFoundException {
        Server server = new Server(InetAddress.getLoopbackAddress());
        Socket socket = new Socket();
        Session session = new Session(server, socket, InetAddress.getLoopbackAddress());

        // Rediriger System.in pour simuler l'entrée utilisateur
        String userInput = "John";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        // Rediriger System.out pour récupérer la sortie du système
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        // Test userExistant
        boolean result = session.userExistant(InetAddress.getLoopbackAddress(), "John");

        assert result : "Erreur lors du test de userExistant (nouvel utilisateur)";
        assert output.toString().contains("utilisateur inconnu") : "Erreur lors du test de userExistant (message pour nouvel utilisateur)";

        result = session.userExistant(InetAddress.getLoopbackAddress(), "John");

        assert !result : "Erreur lors du test de userExistant (utilisateur existant)";
        assert output.toString().contains("utilisateur existant") : "Erreur lors du test de userExistant (message pour utilisateur existant)";

        System.out.println("Test de userExistant réussi");
    }
}

