package src_class;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import src_class.src_class_modele.Client;
import src_class.src_class_modele.Message;

public class Session extends Thread{
    private Server serv;
    private Socket socket;
    private BufferedReader entre;
    private PrintWriter sortie;
    private InetAddress ip;

    public Session(Server serv, Socket socket, InetAddress ip) {
        try{
        this.serv = serv;
        this.socket = socket;
        this.ip = ip;
        this.entre = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.sortie = new PrintWriter(socket.getOutputStream(), false);
        }catch(Exception e){
            System.out.println("Erreur lors de la création de la session");
        }
    }

    public Server getServer() {
        return this.serv;
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void run(){
        System.out.println(">> Session.mainSession entre avec l'adresse ip du client : " + String.valueOf(ip));
        try{

            String usernameClient = entre.readLine();
            System.out.println("\nClient avec username : " + usernameClient + " se connecte / s'inscrit\n");
            Client client = this.userExistant(this.ip, usernameClient);

            while(true){
                String message = entre.readLine();
                Message messageReconsituer = this.reconstitueMessage(message);

                if(messageReconsituer.getContenu().startsWith("quit") || messageReconsituer.getContenu().startsWith("QUIT")){
                    System.out.println("<< Session.mainSession sort avec reponse : Bye !");
                    break;
                }

                else if(messageReconsituer.getContenu().startsWith("profil") || messageReconsituer.getContenu().startsWith("PROFIL")){
                    System.out.println(">> Préparation de l'envoi du profil utilisateur");
                    String username = client.getUsername();
                    int abonnes = client.getAbonnes().size();
                    int abonnements = client.getAbonnement().size();
                    String profil = "Profil : ;Username : " + username+";Abonnés : " + String.valueOf(abonnes)+";Abonnements : " + String.valueOf(abonnements)+";";
                    System.out.println("<< Finition de l'envoi du profil utilisateur sans erreur");
                    sortie.println(profil + "\n");
                    sortie.flush();
                }

                else if (messageReconsituer.getContenu().startsWith("/")) {
                    this.serv.estUneCommandeExistante(messageReconsituer.getContenu().substring(1, messageReconsituer.getContenu().length()) + " " + 
                                                      messageReconsituer.getNomExpediteur());
                    sortie.println("Message reçu\n");
                    sortie.flush();
                }

                else{
                    this.serv.getMessageBDD().ajouterMessage(messageReconsituer);
                    sortie.println("Message reçu\n");
                    sortie.flush();
                }
            }
            this.entre.close();
            this.sortie.close();
            this.socket.close();

        }catch(Exception e){
            System.out.println("<< Session.mainSession sort en exeption");
            System.out.println("Erreur lors de la création de la session");
        }
    }

    public Message reconstitueMessage(String messageSrc) {
        System.out.println(">> Session.reconstitueMessage entre avec le message : " + messageSrc);
        String[] lignes = messageSrc.split(";");
        List<String> listeLignes = new ArrayList<>();
        Collections.addAll(listeLignes, lignes);
        Message res = new Message(listeLignes.get(0), listeLignes.get(1), Integer.parseInt(listeLignes.get(3)));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date date = formatter.parse(listeLignes.get(2));
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            res.setDate(sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        res.setId(Integer.parseInt(listeLignes.get(4)));
        System.out.println("<< Session.reconstitueMessage sort avec le message : " + res);
        return res;
    }

    public Client userExistant(InetAddress ip, String username) throws UnknownHostException, SQLException {
        System.out.println(">> Session.userExistant entre avec l'adresse ip " + String.valueOf(ip) + " et le username " + username);
        boolean estExistant = this.serv.getClientBDD().estClientExistant(username);
        if (estExistant) {
            Client client = this.serv.getClientBDD().chargeInfos(ip, username);
            System.out.println("<< Session.userExistant sort avec un utilisateur existant");
            return client;
        }
        else {
            this.serv.getClientBDD().ajouterClient(username, ip);
            Client client = this.serv.getClientBDD().chargeInfos(ip, username);
            System.out.println("<< Session.userExistant sort avec un utilisateur inconnu");
            return client;
        }
    }
}
