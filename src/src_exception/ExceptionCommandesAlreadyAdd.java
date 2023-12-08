package src_exception;

public class ExceptionCommandesAlreadyAdd extends Exception {
    public ExceptionCommandesAlreadyAdd(String commande) {
        System.out.println("Ce serveur peut déjà effectué la commande [" + commande + "]. Merci de ne pas dupliquer des commandes");
    }
}
