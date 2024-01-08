package src_test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import src_class.Client;
import src_exception.ExceptionFollowUser;

public class MainClient {
    public static void main(String[] args) throws UnknownHostException {
        Client premierClient = new Client(InetAddress.getByName("127.0.0.1"), "steven");
        Client deuxiemeClient = new Client(InetAddress.getByName("192.168.0.1"), "gael");

        System.out.println(premierClient);
        System.out.println(deuxiemeClient);

        Client clienTestSamuel = new Client(InetAddress.getByName(""), "samuel");
        Client clienTestTom = new Client(InetAddress.getByName(""), "tom");

        try {
            System.out.println(premierClient.ajouteAbonnement(clienTestSamuel)); // true
            System.out.println(premierClient.ajouteAbonnement(clienTestTom));
            System.out.println(premierClient.ajouteAbonnement(clienTestSamuel)); // false car dèjà ajouté, lance l'exception

            System.out.println(deuxiemeClient.ajouteAbonnement(clienTestSamuel)); // true
            System.out.println(deuxiemeClient.ajouteAbonne(clienTestTom));
        } catch (ExceptionFollowUser e) {}
    }
}