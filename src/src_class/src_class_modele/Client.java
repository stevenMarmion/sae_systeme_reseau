package src_class.src_class_modele;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.InetAddress;

import src_exception.ExceptionFollowUser;
import src_exception.ExceptionUnfollowUser;
import src_exception.ExceptionUnfoundMessage;

public class Client {
    private InetAddress ip;
    private String username;
    private Socket socket;
    private ArrayList<Client> abonnement;
    private ArrayList<Client> abonnes;
    private ArrayList<Message> mesMessage;

    /**
     * Constructeur de la classe Client.
     * @param ip L'adresse IP associée au client.
     * @param username Le nom d'utilisateur du client.
     */
    public Client(InetAddress ip, String username) {
        this.ip = ip;
        this.username = username;
        this.abonnement = new ArrayList<>();
        this.abonnes = new ArrayList<>();
        this.mesMessage = new ArrayList<>();
    }

    /**
     * Récupère l'adresse IP du client.
     * @return L'adresse IP du client.
     */
    public InetAddress getIp() {
        return this.ip;
    }

    /**
     * Récupère le nom d'utilisateur du client.
     * @return Le nom d'utilisateur du client.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Récupère la liste des personnes suivies par le client.
     * @return La liste des personnes suivies par le client.
     */
    public ArrayList<Client> getAbonnement() {
        return this.abonnement;
    }

    /**
     * Récupère la liste des abonnés du client.
     * @return La liste des abonnés du client.
     */
    public ArrayList<Client> getAbonnes() {
        return this.abonnes;
    }

    /**
     * permet de vérifier si l'identifiant fourni corresponds bien a un des messages du client
     * @param idMessage identifiant du message 
     * @return le message si il existe , lance une exception si il n'existe pas 
     * @throws ExceptionUnfoundMessage aucun message avce cet identifiant trouver
     */
    public Message vérifMessage(int idMessage) throws ExceptionUnfoundMessage{
        for(Message m : this.mesMessage){
            if(m.getId()==idMessage){
                return m;
            }
        }
        throw new ExceptionUnfoundMessage(idMessage+"");
    }
    /**
     * permet de supprimer un message
     * @param idMessage l'identifiant du message a supprimer
     * @return true si le message a été supprimer et false si il ne la pas été
     */
    public boolean supprimerMessage(int idMessage){
        try {
            Message m = vérifMessage(idMessage);
            return this.mesMessage.remove(m);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Ajoute une personne suivie par le client.
     * @param utilisateur Le client à ajouter aux personnes suivies.
     * @return true si l'ajout a réussi, sinon lance une exception.
     * @throws ExceptionFollowUser si l'utilisateur est déjà suivi par ce client.
     */
    public boolean ajouteAbonnement(Client utilisateur) throws ExceptionFollowUser {
        if (!getAbonnement().contains(utilisateur)) {
            getAbonnement().add(utilisateur);
            return true;
        }
        throw new ExceptionFollowUser(utilisateur);
    }
    /**
     * supprime une personne suivie par le client
     * @param utilisateur le client a supprimer des personnes suivies.
     * @return true si la suppression a réussi, sinon lance une exception.
     * @throws ExceptionUnfollowUsersi l'utilisateur n'est pas suivie par l'utilisateur.
     */
    public boolean supprimeAbonnement(Client utilisateur) throws ExceptionUnfollowUser {
        if (!getAbonnement().contains(utilisateur)) {
            getAbonnement().add(utilisateur);
            return true;
        }
        throw new ExceptionUnfollowUser(utilisateur);
    }

    /**
     * Ajoute un abonné au client.
     * @param utilisateur Le client à ajouter à la liste des abonnés.
     * @return true si l'ajout a réussi, sinon false.
     */
    public boolean ajouteAbonne(Client utilisateur) {
        if (!getAbonnes().contains(utilisateur)) {
            getAbonnes().add(utilisateur);
            return true;
        }
        return false;
    }

    /**
     * Met à jour l'adresse IP du client.
     * @param newIp La nouvelle adresse IP à définir.
     */
    public void setIp(InetAddress newIp) {
        if (!getIp().equals(newIp)) {
            this.ip = newIp;
        }
    }

    /**
     * Met à jour le nom d'utilisateur du client.
     * @param newUsername Le nouveau nom d'utilisateur à définir.
     */
    public void setUsername(String newUsername) {
        if (!getUsername().equals(newUsername)) {
            this.username = newUsername;
        }
    }

        /**
     * Met à jour la liste des personnes suivies par le client.
     * 
     * @param abonnement La nouvelle liste des personnes suivies à définir pour le client.
     */
    public void setAbonnement(ArrayList<Client> abonnement) {
        this.abonnement = abonnement;
    }

    /**
     * Met à jour la liste des abonnés du client.
     * 
     * @param abonnes La nouvelle liste des abonnés à définir pour le client.
     */
    public void setAbonnes(ArrayList<Client> abonnes) {
        this.abonnes = abonnes;
    }

    /**
     * Met à jour la liste des messages associés au client.
     * 
     * @param mesMessage La nouvelle liste de messages à associer au client.
     */
    public void setMesMessage(ArrayList<Message> mesMessage) {
        this.mesMessage = mesMessage;
    }


    public void lireMessage() {
        try {
            Socket socket = new Socket("127.0.0.1", 4445);
            InputStreamReader stream = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(stream);
            String message = reader.readLine();
            System.out.println(message);
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ecrireMessage(String message) {
        try {
            Socket socket = new Socket("localhost", 5555);
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println(message);
            writer.flush();
            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lien(String host,String port){
        try {
            Scanner sc = new Scanner(System.in);
            this.socket= new Socket(host, Integer.parseInt(port));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            System.out.println("message a envoyer:\n");
            while(true){
                String text=sc.nextLine();
                writer.println(text+"\n");
                writer.flush();
                String message = reader.readLine();
                System.out.println(message);
                System.out.println("message a envoyer:\n");
            }
        } catch (Exception e) {}
    }

    /**
     * Vérifie si deux objets Client sont égaux.
     * @param obj L'objet à comparer.
     * @return true si les objets Client sont égaux, sinon false.
     */
    @Override
    public boolean equals(Object obj) {
        Client autreClient = (Client) obj;
        return (getUsername().equals(autreClient.getUsername()) &&
                getIp().equals(autreClient.getIp()) &&
                getAbonnement().equals(autreClient.getAbonnement()) &&
                getAbonnes().equals(autreClient.getAbonnes()));
    }

    /**
     * Retourne une représentation en chaîne du client.
     * @return Une chaîne représentant l'adresse IP et le nom d'utilisateur du client.
     */
    @Override
    public String toString() {
        return "Client d'ip : " + getIp() + " et de username : " + getUsername();
    }
}
