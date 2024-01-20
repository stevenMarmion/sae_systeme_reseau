package src_main;

import java.net.InetAddress;

import src_class.Server;

public class MainTestServeur {
    public static void main(String[] args) {
        System.out.println(">> MainTestServeur.main entre ");
        try { 
            Server serv = new Server(InetAddress.getByName("localhost"));
            serv.run();
        } catch (Exception e) {
            System.out.println("<< MainTestServeur.main sort en exception ");
        }
        
    }
}
