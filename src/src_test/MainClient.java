package src_test;

import src_class.Client;
import src_exception.ExceptionFollowUser;

public class MainClient {
    public static void main(String[] args) {
        Client premierClient = new Client("127.0.0.1", "steven");
        Client deuxiemeClient = new Client("192.168.0.1", "gael");

        System.out.println(premierClient);
        System.out.println(deuxiemeClient);

        Client clienTestSamuel = new Client("", "samuel");
        Client clienTestTom = new Client("", "tom");

        try {
            System.out.println(premierClient.ajouteAbonnement(clienTestSamuel.getUsername())); // true
            System.out.println(premierClient.ajouteAbonnement(clienTestTom.getUsername()));
            System.out.println(premierClient.ajouteAbonnement(clienTestSamuel.getUsername())); // false car dèjà ajouté, lance l'exception

            System.out.println(deuxiemeClient.ajouteAbonnement(clienTestSamuel.getUsername())); // true
            System.out.println(deuxiemeClient.ajouteAbonne(clienTestTom.getUsername()));
        } catch (ExceptionFollowUser e) {}
    }
}