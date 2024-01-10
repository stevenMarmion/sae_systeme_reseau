package src_class;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import src_exception.ExceptionCommandesAlreadyAdd;
import src_exception.ExceptionIpAlreadyDefined;
import src_exception.ExceptionIpEmpty;

import src_class.src_class_modeleBDD.*;
import src_class.src_class_commande.Commande;
import src_class.src_class_commande.CommandeDeleteClient;
import src_class.src_class_commande.CommandeDeleteServer;
import src_class.src_class_commande.CommandeFollow;
import src_class.src_class_commande.CommandeLike;
import src_class.src_class_commande.CommandeRemove;
import src_class.src_class_commande.CommandeUnfollow;
import src_class.src_class_modele.*;

public class Server {
    private InetAddress ipServer;
    private ArrayList<Commande> commandesServer;
    private ArrayList<Commande> commandesClient;
    private ServerSocket serveurSocket;
    private ClientBDD clientBDD;
    private MessageBDD messageBDD;
    private ConnectionBDD connectionBDD;

    public Server(InetAddress ip, ArrayList<Commande> commandesServer, ArrayList<Commande> commandesClient) throws ClassNotFoundException {
        this.ipServer = ip;
        this.commandesServer = commandesServer;
        this.commandesClient = commandesClient;
        // this.connectionBDD = new ConnectionBDD();
        // this.clientBDD = new ClientBDD(connectionBDD);
        // this.messageBDD = new MessageBDD(connectionBDD);
    }

    public Server(InetAddress ip) throws ClassNotFoundException {
        this.ipServer = ip;
        this.commandesServer = new ArrayList<>();
        this.commandesClient = new ArrayList<>();
        this.connectionBDD = new ConnectionBDD();
        this.clientBDD = new ClientBDD(connectionBDD);
        this.messageBDD = new MessageBDD(connectionBDD);

        Commande commandeDelete = new CommandeDeleteServer("delete", "1");
        Commande commandeRemove = new CommandeRemove("remove", "2");
        this.commandesServer.add(commandeDelete);
        this.commandesServer.add(commandeRemove);

        Commande commandeFollow = new CommandeFollow();
        Commande commandeUnfollow = new CommandeUnfollow();
        Commande commandeLike = new CommandeLike("like", "5");
        Commande commandeDeleteMessage = new CommandeDeleteClient("delete", "6", this.connectionBDD, this.messageBDD);
        this.commandesClient.add(commandeFollow);
        this.commandesClient.add(commandeUnfollow);
        this.commandesClient.add(commandeLike);
        this.commandesClient.add(commandeDeleteMessage);
    }

    public InetAddress getIpServer() {
        return this.ipServer;
    } 

    public ArrayList<Commande> getCommandesServer() {
        return this.commandesServer;
    }

    public ArrayList<Commande> getCommandesClient() {
        return this.commandesClient;
    }

    public boolean setIpServer(InetAddress ip) throws ExceptionIpAlreadyDefined, ExceptionIpEmpty {
        if (ip.isAnyLocalAddress()) {
            throw new ExceptionIpEmpty();
        }
        else if (!getIpServer().equals(ip)) {
            this.ipServer = ip;
            return true;
        }
        else {
            throw new ExceptionIpAlreadyDefined(ip);
        }
    }

    public boolean ajouteNouvelleCommandesServer(Commande commande1) throws ExceptionCommandesAlreadyAdd {
        if (!getCommandesServer().contains(commande1)) {
            getCommandesServer().add(commande1);
            return true;
        }
        throw new ExceptionCommandesAlreadyAdd(commande1);
    }

    public boolean ajouteNouvelleCommandesClient(Commande commande1) throws ExceptionCommandesAlreadyAdd {
        if (!getCommandesClient().contains(commande1)) {
            getCommandesClient().add(commande1);
            return true;
        }
        throw new ExceptionCommandesAlreadyAdd(commande1);
    }

    public boolean ajouteListCommandes(ArrayList<Commande> listeCommandes) throws ExceptionCommandesAlreadyAdd {
        if (getCommandesServer().isEmpty()) {
            this.commandesServer = listeCommandes;
            return true;
        }
        else if (!getCommandesServer().isEmpty()){
            for (Commande commande : listeCommandes) {
                if (!getCommandesServer().contains(commande)) {
                    ajouteNouvelleCommandesServer(commande);
                }
                else {
                    throw new ExceptionCommandesAlreadyAdd(commande);
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    public boolean verifieIP(InetAddress ip) {
        // A compléter avec la classe de verification d'IP
        return true;
    }

    public void run() throws SQLException{
        System.out.println(">> Server.run entre avec les commandes : " + this.getCommandesServer() +  " et tout les commandes client : " + this.getCommandesClient());
        while (true) {
            this.enAttenteConnexion(5555);
        }
    }

    public void enAttenteConnexion(int numPort) throws SQLException {
        System.out.println(">> Server.enAttenteConnexion entre sur le port " + String.valueOf(numPort));
        try {
            this.serveurSocket = new ServerSocket(numPort);
            while(true) {
                Socket socketClient = this.serveurSocket.accept();
                System.out.println("connexion d'un client");
                InetAddress ip = socketClient.getInetAddress();
                Session sess = new Session(this,socketClient);
                sess.mainSession(ip);
            }
        }catch (IOException e) {
            System.out.println("<< Server.enAttenteConnexion sort en exeption");
            e.printStackTrace();
        }
    }

    public boolean userExistant(InetAddress ip, String username) throws UnknownHostException, SQLException {
        System.out.println(">> Server.userExistant entre avec l'adresse ip " + String.valueOf(ip) + " et le username " + username);
        boolean estExistant = this.clientBDD.estClientExistant(username);
        System.out.println(username);
        if (estExistant) {
            this.clientBDD.chargeInfos(ip, username);
            System.out.println("<< Server.userExistant sort avec un utilisateur existant");
            return true;
        }
        else {
            this.clientBDD.ajouterClient(username, ip);
            System.out.println("<< Server.userExistant sort avec un utilisateur inconnu");
            return false;
        }
    }

    public void ajouteMessage(Message message) throws SQLException {
        System.out.println(">> Server.ajouteMessage entre avec le parametre " + message);
        this.messageBDD.ajouterMessage(message);
        System.out.println("<< Server.ajouteMessage sort");
    }

    public boolean estUneCommandeExistante(String commandeAvecParametre) {
        System.out.println(">> Server.estUneCommandeExistante entre avec le parametre " + commandeAvecParametre);

        String[] nomCommande = commandeAvecParametre.split(" ");
        List<String> listeLignes = new ArrayList<>();
        Collections.addAll(listeLignes, nomCommande);

        String commande = listeLignes.get(0);
        String param = listeLignes.get(1);
        String username = listeLignes.get(2);

        for (Commande commandeClient : getCommandesClient()) {
            if (commandeClient.getNom().equals(commande)) {
                String response = commandeClient.agis(param, username);
                if (response == null) {
                    System.out.println("<< Server.estUneCommandeExistante sort sans supprimé");
                    return false;
                }
                else {
                    System.out.println("<< Server.estUneCommandeExistante sort en ayant supprimé");
                    return true;
                }
            }
        }
        System.out.println("<< Server.estUneCommandeExistante sort sans supprimé");
        return false;
    }

    @Override
    public String toString() {
        return "Serveur définit par l'IP : " + getIpServer() + ", liste des commandes : " + getCommandesServer();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Server server = new Server(InetAddress.getByName("localhost"));
        while (true) {
            server.enAttenteConnexion(5555);
        }
    }
}
