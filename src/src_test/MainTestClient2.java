package src_test;

import java.net.InetAddress;

import src_class.Client;

public class MainTestClient2 {
    public static void main(String[] args) {
        try {
            Client client = new Client(InetAddress.getByName("127.0.0.2"), "test2");
            client.lien("localhost", "5555");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
