package src_exception;

public class ExceptionIpEmpty extends Exception {
    public ExceptionIpEmpty() {
        System.out.println("L'addres IP que vous essayez d'établir pour le serveur est vide, veuillez vérifiez l'IP mise.");
    }
}
