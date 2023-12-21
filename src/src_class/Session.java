package src_class;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

    public void mainSession(){
        try{
            while(true){
                String message = entre.readLine();
                if(message == "quit"){
                    break;
                }
                else{
                    System.out.println(message);
                    sortie.println("reçu\n");
                    sortie.flush();
                }
            }
        }catch(Exception e){
            System.out.println("Erreur lors de la création de la session");
        }
    }
}
