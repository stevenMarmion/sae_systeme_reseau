package src_exception;

public class ExceptionIpEmpty extends Exception {
    public ExceptionIpEmpty() {
        System.out.println("L'addresse IP que vous essayez d'établir pour le serveur est vide ou null, veuillez vérifiez l'IP mise.");
    }
}
