package src_test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import src_class.Server;
import src_class.src_class_commande.Commande;
import src_class.src_class_commande.CommandeDeleteServer;
import src_class.src_class_commande.CommandeRemove;
import src_exception.ExceptionCommandesAlreadyAdd;

public class MainServer {
    public static void main(String[] args) throws UnknownHostException, ClassNotFoundException, SQLException {
        Server mainServer = new Server(InetAddress.getByName("localhost"));

        ArrayList<Commande> listeCommandes = new ArrayList<>();
        Commande commandeDelete = new CommandeDeleteServer("delete", "1");
        Commande commandeRemove = new CommandeRemove("remove", "2");

        listeCommandes.add(commandeRemove);
        listeCommandes.add(commandeDelete);

        try {
            mainServer.ajouteListCommandes(listeCommandes); // true 
        } catch (ExceptionCommandesAlreadyAdd e) {}

        try {
            mainServer.setIpServer(InetAddress.getByName("192.168.0.2")); //true
            mainServer.setIpServer(InetAddress.getByName("192.168.0.2")); // lance une erreur car le serveur est d&jà sur cette IP.
        } catch (Exception e) {}
    }
}
