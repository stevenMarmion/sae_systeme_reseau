package src_test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import src_class.Server;
import src_exception.ExceptionCommandesAlreadyAdd;

public class MainServer {
    public static void main(String[] args) throws UnknownHostException {
        Server mainServer = new Server(InetAddress.getByName("localhost"));

        ArrayList<String> listeCommandes = new ArrayList<>();
        String commande1 = "delete";
        String commande2 = "add";
        String commande3 = "follow";
        listeCommandes.add(commande1);
        listeCommandes.add(commande2);
        listeCommandes.add(commande3);

        try {
            mainServer.ajouteListCommandes(listeCommandes); // true
            mainServer.ajouteNouvelleCommandesServer(commande1); // lance une exception car la commande est déjà instanciée

            mainServer.ajouteNouvelleCommandesServer("bonjour");   
        } catch (ExceptionCommandesAlreadyAdd e) {}

        try {
            mainServer.setIpServer(InetAddress.getByName("192.168.0.2")); //true
            mainServer.setIpServer(InetAddress.getByName("192.168.0.2")); // lance une erreur car le serveur est d&jà sur cette IP.
        } catch (Exception e) {}
    }
}
