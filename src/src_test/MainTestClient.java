package src_test;

import java.net.InetAddress;

import src_class.src_class_modele.Client;

public class MainTestClient {
    public static void main(String[] args) {
        System.out.println(">> MainTestClient.main entre ");
        try {
            String username = "Steven";
            Client client = new Client(InetAddress.getByName("127.0.0.1"), username);
            client.lien("localhost", "5555", username);
        } catch (Exception e) {
            System.out.println(">> MainTestClient.main sort en exeption ");
        }
        
    }
}
