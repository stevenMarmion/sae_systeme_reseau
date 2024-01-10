package src_class;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import src_exception.ExceptionCommandesAlreadyAdd;
import src_exception.ExceptionIpAlreadyDefined;
import src_exception.ExceptionIpEmpty;

import src_class.src_class_modeleBDD.*;
import src_class.src_class_modele.*;

public class Server {
    private InetAddress ipServer;
    private ArrayList<String> commandesServer;
    private ServerSocket serveurSocket;
    private ClientBDD clientBDD;
    private MessageBDD messageBDD;
    private ConnectionBDD connectionBDD;

    public Server(InetAddress ip, ArrayList<String> commandesServer) throws ClassNotFoundException {
        this.ipServer = ip;
        this.commandesServer = commandesServer;
        this.connectionBDD = new ConnectionBDD();
        this.clientBDD = new ClientBDD(connectionBDD);
        this.messageBDD = new MessageBDD(connectionBDD);
    }

    public Server(InetAddress ip) throws ClassNotFoundException {
        this.ipServer = ip;
        this.commandesServer = new ArrayList<>();
        this.connectionBDD = new ConnectionBDD();
        this.clientBDD = new ClientBDD(connectionBDD);
        this.messageBDD = new MessageBDD(connectionBDD);
    }

    public InetAddress getIpServer() {
        return this.ipServer;
    } 

    public ArrayList<String> getCommandesServer() {
        return this.commandesServer;
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

    public boolean ajouteNouvelleCommandesServer(String newCommande) throws ExceptionCommandesAlreadyAdd {
        if (!getCommandesServer().contains(newCommande)) {
            getCommandesServer().add(newCommande);
            return true;
        }
        throw new ExceptionCommandesAlreadyAdd(newCommande);
    }

    public boolean ajouteListCommandes(ArrayList<String> listeCommandesServer) throws ExceptionCommandesAlreadyAdd {
        if (getCommandesServer().isEmpty()) {
            this.commandesServer = listeCommandesServer;
            return true;
        }
        else if (!getCommandesServer().isEmpty()){
            for (String commande : listeCommandesServer) {
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

    public void run(String username) throws SQLException{
        while (true) {
            this.enAttenteConnexion(5555, username);
        }
    }

    public void enAttenteConnexion(int numPort, String username) throws SQLException {
        try {
            this.serveurSocket = new ServerSocket(numPort);
            while(true) {
                Socket socketClient = this.serveurSocket.accept();
                System.out.println("connexion d'un client");
                InetAddress ip = socketClient.getInetAddress();
                this.userExistant(ip, username);
                Session sess = new Session(this,socketClient);
                sess.mainSession();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean userExistant(InetAddress ip, String username) throws UnknownHostException, SQLException {
        boolean estExistant = this.clientBDD.estClientExistant(username);
        if (estExistant) {
            this.clientBDD.chargeInfos(ip, username);
            return true;
        }
        else {
            this.clientBDD.ajouterClient(username, ip);
            return false;
        }
    }

    public void ajouteMessage(Message message) throws SQLException {
        this.messageBDD.ajouterMessage(message);
    }

    @Override
    public String toString() {
        return "Serveur définit par l'IP : " + getIpServer() + ", liste des commandes : " + getCommandesServer();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Server server = new Server(InetAddress.getByName("localhost"));
        String nouveauUsername = "Steven";
        while (true) {
            server.enAttenteConnexion(5555, nouveauUsername);
        }
    }
}
