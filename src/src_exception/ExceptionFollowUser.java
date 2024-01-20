package src_exception;

import src_class.src_class_modele.Client;

public class ExceptionFollowUser extends Exception {
    public ExceptionFollowUser(Client nomUtilisateur) {
        System.out.println("L'utilisateur " +  nomUtilisateur +" que vous essayez d'ajouter est déjà dans la liste des personnes que vous suivez");
    }
}
