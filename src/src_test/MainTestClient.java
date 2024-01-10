package src_test;

import java.net.InetAddress;

import src_class.src_class_modele.Client;

public class MainTestClient {
    public static void main(String[] args) {
        try {
            Client client = new Client(InetAddress.getByName("127.0.0.1"), "test");
            client.lien("localhost", "5555");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
