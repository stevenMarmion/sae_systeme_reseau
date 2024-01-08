package src_exception;

import src_class.Client;

public class ExceptionUnfollowUser extends Exception{
    public ExceptionUnfollowUser(Client nomUtilisateur){
        System.out.println("l'utilisateur "+nomUtilisateur+" ne se trouve pas dans vos abonnement");
    }
    
}
