package src_exception;

import java.net.InetAddress;

public class ExceptionIpAlreadyDefined extends Exception {
    public ExceptionIpAlreadyDefined(InetAddress ip) {
        System.out.println("Ce serveur est déjà définit sur l'ip " + ip);
    }
}
