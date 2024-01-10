package src_class;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import src_class.src_class_modele.Message;

public class Session {
    private Server serv;
    private Socket socket;
    private BufferedReader entre;
    private PrintWriter sortie;

    public Session(Server serv, Socket socket) {
        try{
        this.serv = serv;
        this.socket = socket;
        this.entre = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.sortie = new PrintWriter(socket.getOutputStream(), false);
        }catch(Exception e){
            System.out.println("Erreur lors de la création de la session");
        }
    }

    public void mainSession(InetAddress ip){
        System.out.println(">> Session.mainSession entre avec l'adresse ip du client : " + String.valueOf(ip));
        try{
            String usernameClient = entre.readLine();
            System.out.println("\nClient avec username : " + usernameClient + " se connecte / s'inscrit\n");
            //this.serv.userExistant(ip, usernameClient);
            while(true){
                String message = entre.readLine();
                Message messageReconsituer = this.reconstitueMessage(message);

                if(messageReconsituer.getContenu() == "quit"){
                    System.out.println("<< Session.mainSession sort avec reponse : Bye !");
                    break;
                }
                else if (messageReconsituer.getContenu().startsWith("/")) {
                    System.out.println("\non a une commande !!");
                    this.serv.estUneCommandeExistante(messageReconsituer.getContenu().substring(1, messageReconsituer.getContenu().length()) + " " + 
                                                      messageReconsituer.getNomExpediteur());
                    sortie.println("\nMessage reçu\n");
                    sortie.flush();
                }
                else{
                    sortie.println("\nMessage reçu\n");
                    sortie.flush();
                }
            }
        }catch(Exception e){
            System.out.println("<< Session.mainSession sort en exeption");
            System.out.println("Erreur lors de la création de la session");
        }
    }

    public Message reconstitueMessage(String messageSrc) {
        System.out.println(">> Session.reconstitueMessage entre avec le message : " + messageSrc);
        String[] lignes = messageSrc.split(",");
        List<String> listeLignes = new ArrayList<>();
        Collections.addAll(listeLignes, lignes);
        Message res = new Message(listeLignes.get(0), listeLignes.get(1), Integer.parseInt(listeLignes.get(3)));
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        try {
            Date date = formatter.parse(listeLignes.get(2));
            res.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        res.setId(Integer.parseInt(listeLignes.get(4)));
        System.out.println("<< Session.reconstitueMessage sort avec le message : " + res);
        return res;
    }
}
