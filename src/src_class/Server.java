package src_class;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import src_exception.ExceptionCommandesAlreadyAdd;
import src_exception.ExceptionIpAlreadyDefined;
import src_exception.ExceptionIpEmpty;

public class Server {
    private InetAddress ipServer;
    private ArrayList<String> commandesServer;

    public Server(InetAddress ip, ArrayList<String> commandesServer) {
        this.ipServer = ip;
        this.commandesServer = commandesServer;
    }

    public Server(InetAddress ip) {
        this.ipServer = ip;
        this.commandesServer = new ArrayList<>();
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

    public void enAttenteConnexion(int numPort) {
        try {
            ServerSocket socketServer = new ServerSocket(numPort);
            Socket socketClient = socketServer.accept();
            System.out.println("connexion d'un client");
            socketClient.close();
            socketServer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Serveur définit par l'IP : " + getIpServer() + ", liste des commandes : " + getCommandesServer();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(InetAddress.getByName("localhost"));
        while (true) {
            server.enAttenteConnexion(5555);
        }
    }
}
