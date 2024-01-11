package src_exception;

import src_class.src_class_commande.Commande;

public class ExceptionCommandesAlreadyAdd extends Exception {
    public ExceptionCommandesAlreadyAdd(Commande newCommande) {
        System.out.println("Ce serveur peut déjà effectué la commande [" + newCommande + "]. Merci de ne pas dupliquer des commandes");
    }
}
