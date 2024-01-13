package src_test;

import java.net.InetAddress;

import src_class.Client;

public class MainTestClient1 {
    public static void main(String[] args) {
        try {
            Client client = new Client(InetAddress.getByName("127.0.0.1"), "test1");
            client.lien("localhost", "5555");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
