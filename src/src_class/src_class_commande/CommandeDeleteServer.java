package src_class.src_class_commande;

import src_class.src_class_modele.Client;
import src_class.src_class_modeleBDD.*;

public class CommandeDeleteServer implements Commande {
    private String nom;
    private String code;
    private MessageBDD messageBDD;
    private ClientBDD clientBDD;

    public CommandeDeleteServer(String nom, String code, MessageBDD messageBDD, ClientBDD clientBDD){
        this.nom=nom;
        this.code=code;
        this.messageBDD = messageBDD;
        this.clientBDD = clientBDD;
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
    public String agis(String param, String username) {
      try {
        Integer idMessage = Integer.parseInt(param);
        Client clientWithUsername = this.clientBDD.getClient(username);
        return this.deleteMessageByServeur(clientWithUsername, idMessage);
      } catch (Exception e) {
        
      }
      return null;
    }

    public String deleteMessageByServeur(Client client, int idMessage){
      String data;
      try{
        this.messageBDD.effacerMessage(idMessage);
        data="commande: \n type:'"+this.getNom()+"' \n idMessage:'"+idMessage+"' \n utilisateurCommande = Serveur";
        return data;
      }
      catch (Exception e) {return null;}
    }
}
