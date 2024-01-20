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
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private InetAddress ipServer;
    private ArrayList<Commande> commandesServer;
    private ArrayList<Commande> commandesClient;
    private ServerSocket serveurSocket;
    private ClientBDD clientBDD;
    private MessageBDD messageBDD;
    private ConnectionBDD connectionBDD;
    private ReentrantLock lock = new ReentrantLock();


    public Server(InetAddress ip, ArrayList<Commande> commandesServer, ArrayList<Commande> commandesClient) throws ClassNotFoundException, SQLException {
        this.ipServer = ip;
        this.commandesServer = commandesServer;
        this.commandesClient = commandesClient;
        this.connecteServerBDD();
    }

    public Server(InetAddress ip) throws ClassNotFoundException, SQLException {
        System.out.println(">> Server.constructor entre ");
        this.ipServer = ip;
        this.connecteServerBDD();
        this.instancieCommandesClient(this);
        this.instancieCommandesServer(this);
        System.out.println(">> Server.constructor sort sans erreur");
    }

    public void connecteServerBDD() throws ClassNotFoundException, SQLException {
        this.connectionBDD = new ConnectionBDD();
        this.connectionBDD.connecter("localhost", "reseau_social", "root", "simon");
        this.clientBDD = new ClientBDD(connectionBDD);
        this.messageBDD = new MessageBDD(connectionBDD);
    }

    public void instancieCommandesServer(Server serveur) {
        this.commandesServer = new ArrayList<>();
        Commande commandeDelete = new CommandeDeleteServer(serveur);
        Commande commandeRemove = new CommandeRemove(serveur);
        this.commandesServer.add(commandeDelete);
        this.commandesServer.add(commandeRemove);
    }

    public void instancieCommandesClient(Server serveur) {
        this.commandesClient = new ArrayList<>();
        Commande commandeFollow = new CommandeFollow(serveur);
        Commande commandeUnfollow = new CommandeUnfollow(serveur);
        Commande commandeLike = new CommandeLike(serveur);
        Commande commandeDeleteMessage = new CommandeDeleteClient(serveur);
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

    public MessageBDD getMessageBDD() {
        return this.messageBDD;
    }

    public ClientBDD getClientBDD() {
        return this.clientBDD;
    }

    public synchronized ReentrantLock getReentrantLock(){
        return this.lock;
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
        System.out.println(">> Server.run entre avec " + this.getCommandesServer().size() +  " commandes Server et " + this.getCommandesClient().size() + " commandes Client");
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
                Session sess = new Session(this,socketClient,ip);
                sess.start();
                System.out.println("ok");
            }
        }catch (IOException e) {
            System.out.println("<< Server.enAttenteConnexion sort en exeption");
            e.printStackTrace();
        }
    }

    public String estUneCommandeExistante(String commandeAvecParametre) throws UnknownHostException, SQLException {
        System.out.println(">> Server.estUneCommandeExistante entre avec le parametre " + commandeAvecParametre);

        String[] nomCommande = commandeAvecParametre.split(" ");
        List<String> listeLignes = new ArrayList<>();
        Collections.addAll(listeLignes, nomCommande);

        String commande = "";
        String param = "";
        String username = "";

        if (listeLignes.size()==3) {
            commande = listeLignes.get(0);
            param = listeLignes.get(1);
            username = listeLignes.get(2);
        }
        else {
            System.out.println("<< Server.estUneCommandeExistante sort sans supprimé");
            return null;
        }
        for (Commande commandeClient : getCommandesClient()) {
            if (commandeClient.getNom().equals(commande)) {
                String response = commandeClient.agis(param, username);
                if (response == null) {
                    System.out.println("<< Server.estUneCommandeExistante sort sans supprimé");
                    return null;
                }
                else {
                    System.out.println("<< Server.estUneCommandeExistante sort en ayant supprimé");
                    return "Action réussi !";
                }
            }
        }
        System.out.println("<< Server.estUneCommandeExistante sort sans supprimé");
        return null;
    }

    @Override
    public String toString() {
        return "Serveur définit par l'IP : " + getIpServer() + ", liste des commandes : " + getCommandesServer();
    }
}
