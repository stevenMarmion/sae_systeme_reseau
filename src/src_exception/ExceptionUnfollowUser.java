package src_exception;

public class ExceptionUnfollowUser extends Exception{
    public ExceptionUnfollowUser(String nomUtilisateur){
        System.out.println("l'utilisateur "+nomUtilisateur+" ne se trouve pas dans vos abonnement");
    }
    
}
