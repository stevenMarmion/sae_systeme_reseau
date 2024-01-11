package src_class.src_class_commande;

import java.net.UnknownHostException;
import java.sql.SQLException;

public interface Commande {
    public String getNom();

    public String getCode();

    public String agis(String param, String username) throws UnknownHostException, SQLException;
}