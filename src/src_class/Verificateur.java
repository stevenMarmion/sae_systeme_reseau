/**
 * Ce code provient du site Techiedelight :
 * https://www.techiedelight.com/fr/validate-ip-address-java/
 */
package src_class;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verificateur {
    private static final String IPV4_REGEX =
                    "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
 
    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);
 
    /**
     * Vérifie si une adresse IPv4 est valide.
     * @param ip Adresse IPv4 à vérifier
     * @return true si l'adresse est valide, sinon false
     */
    public static boolean isValidInet4Address(String ip) {
        Matcher matcher = IPv4_PATTERN.matcher(ip);
        return matcher.matches();
    }
}
