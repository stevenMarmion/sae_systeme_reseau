package src_exception;

import src_class.Client;

public class ExceptionFollowUser extends Exception {
    public ExceptionFollowUser(Client client) {
        System.out.println("L'utilisateur " +  client.getUsername() +" que vous essayez d'ajouter est déjà dans la liste des personnes que vous suivez");
    }
}
