package src_test;

import java.net.InetAddress;
import java.util.ArrayList;

import src_class.Server;

public class MainTestServeur {
    public static void main(String[] args) {
        ArrayList<String> listeCommandes = new ArrayList<>();
        try {
            Server serv = new Server(InetAddress.getByName("localhost"),listeCommandes);
            serv.run();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
