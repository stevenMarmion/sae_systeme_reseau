package src_exception;

public class ExceptionIpNonValide extends Exception {
    public ExceptionIpNonValide(String ip) {
        System.out.println("L'IP " + ip + " n'est pas valide, merci de vérifier l'adresse IP entrée");
    }
}
