package src_class.src_class_commande;

import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.src_class_modele.Client;
import src_class.src_class_modeleBDD.*;

public class CommandeLike implements Commande{

    private String nom;
    private String code;
    private MessageBDD messageBDD;
    private ClientBDD clientBDD;

    public CommandeLike(String nom,String code, MessageBDD messageBDD, ClientBDD clientBDD){
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
    public String agis(String param, String username) throws UnknownHostException, SQLException {
      Client clientQuiLike = this.clientBDD.getClient(username);
      int idMessage = Integer.parseInt(param);
      return this.like(clientQuiLike, idMessage);
    }

    /**
     * permet de liker un message
     * @param client le client qui veut liker le message
     * @param idMessage l'identifiant du message qui sera liker
     * @return un message sui sera envoyer au serveur pour qu'il puissent actualiser les likes du message 
     */
    public String like(Client client, int idMessage){
      String data;
      try{
        this.messageBDD.likerMessage(idMessage);
        data="commande: \n type:'"+this.getNom()+"' \n idMessage:'"+idMessage+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      }
      catch (Exception e) {return "erreur";}
    }
}
