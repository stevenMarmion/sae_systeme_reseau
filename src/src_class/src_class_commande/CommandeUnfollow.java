package src_class.src_class_commande;
import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.src_class_modele.Client;
import src_class.src_class_modeleBDD.ClientBDD;

public class CommandeUnfollow implements Commande{

    private String nom;
    private String code;
    private ClientBDD clientBDD;

    public CommandeUnfollow(ClientBDD clientBDD){
        this.nom="unfollow";
        this.code="4";
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
      Client clientExpediteur = this.clientBDD.getClient(username);
      Client clientToUnfollow = this.clientBDD.getClient(param);
      return this.unfollow(clientExpediteur, clientToUnfollow);
    }

    /**
     * permet de unFollow un utilisateur 
     * @param client le client qui veut unfollow
     * @param unfollowUser le nom de l'utilisateur qui sera unfollow
     * @return un message sui sera envoyer au serveur pour qu'il puissent actualiser la liste des abonnes de l'utilisateur
     */
    public String unfollow(Client client, Client unfollowUser){
      String data;
      try{
        this.clientBDD.supprimerAbonnement(client.getUsername(), unfollowUser.getUsername());
        data="commande: \n type:'"+this.getNom()+"' \n utilisateur:'"+unfollowUser+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      }
      catch (Exception e) {return "erreur";}
    }
    

}
