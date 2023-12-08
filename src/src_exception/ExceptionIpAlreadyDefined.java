package src_exception;

public class ExceptionIpAlreadyDefined extends Exception {
    public ExceptionIpAlreadyDefined(String ip) {
        System.out.println("Ce serveur est déjà définit sur l'ip " + ip);
    }
}
