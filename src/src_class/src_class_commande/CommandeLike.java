package src_class.src_class_commande;

import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.src_class_modele.Client;

public class CommandeLike implements Commande{

    private String nom;
    private String code;

    public CommandeLike(String nom,String code){
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

    /**
     * permet de liker un message
     * @param client le client qui veut liker le message
     * @param idMessage l'identifiant du message qui sera liker
     * @return un message sui sera envoyer au serveur pour qu'il puissent actualiser les likes du message 
     */
    public String like(Client client, String idMessage){
      String data;
      try{
        client.vérifMessage(Integer.parseInt(idMessage));
        data="commande: \n type:'"+this.getNom()+"' \n idMessage:'"+idMessage+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      }
      catch (Exception e) {return "erreur";}
    }
}
