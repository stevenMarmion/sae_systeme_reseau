package src_exception;

public class ExceptionFollowUser extends Exception {
    public ExceptionFollowUser(String nomUtilisateur) {
        System.out.println("L'utilisateur " +  nomUtilisateur +" que vous essayez d'ajouter est déjà dans la liste des personnes que vous suivez");
    }
}
