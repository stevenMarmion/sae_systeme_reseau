package src_class.src_class_commande;

import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.src_class_modeleBDD.ClientBDD;
import src_class.src_class_modeleBDD.MessageBDD;

public class CommandeRemove implements Commande {
    private String nom;
    private String code;
    private ClientBDD clientBDD;
    private MessageBDD messageBDD;
    
    public CommandeRemove(String nom, String code, ClientBDD clientBDD, MessageBDD messageBDD){
        this.nom=nom;
        this.code=code;
        this.clientBDD = clientBDD;
        this.messageBDD = messageBDD;
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
      return this.remove(param);
    }

    public String remove(String usernameToDelete){
      String data;
      try{
        this.clientBDD.supprimerUtilisateur(usernameToDelete);
        this.messageBDD.supprimerMessagesUtilisateur(usernameToDelete);
        data="commande: \n type:'"+this.getNom()+"' \n username:'"+usernameToDelete+"' \n utilisateurCommande = Serveur";
        return data;
      }
      catch (Exception e) {return "erreur";}
    }
    
}
