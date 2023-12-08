public class Main {
    public static void main(String[] args) {
        Client premierClient = new Client("127.0.0.1", "steven");
        Client deuxiemeClient = new Client("192.168.0.1", "gael");

        System.out.println(premierClient);
        System.out.println(deuxiemeClient);

        premierClient.ajouteAbonne(new Client("127.0.0.1", "tom"));

        try {
            System.out.println(premierClient.ajouteAbonnement(new Client("", "samuel")));
            System.out.println(premierClient.ajouteAbonnement(new Client("", "tom")));
        } catch (ExceptionFollowUser e) {}
    }
}