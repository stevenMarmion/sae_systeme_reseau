package src_class.src_class_commande;

import java.net.UnknownHostException;
import java.sql.SQLException;

public class CommandeRemove implements Commande {
    private String nom;
    private String code;
    
    public CommandeRemove(String nom, String code){
        this.nom=nom;
        this.code=code;
    }

    @Override
    public String getNom() {
      return this.nom;
    }

    @Override
    public String getCode() {
      return this.code;
    }

    @Override
    public String agis(String param, String username) throws UnknownHostException, SQLException {
      return username;
    }
    
}
