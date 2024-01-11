package src_class.src_class_commande;
import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.src_class_modele.Client;
import src_class.src_class_modeleBDD.ClientBDD;


public class CommandeFollow implements Commande{

    private String nom;
    private String code;
    private ClientBDD clientBDD;

    public CommandeFollow(ClientBDD clientBDD){
        this.nom="follow";
        this.code="3";
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
      Client clientToFollow = this.clientBDD.getClient(param);
      return this.follow(clientExpediteur, clientToFollow);
    }

    /**
     * permet a un client de "suivre" un autre client
     * @param client le client qui veut suivre
     * @param followUser le nom du client qui va etre suivi
     * @return un message qui sera envoyer au serveur pour actuliser la liste des abonnement du followUser
     */
    public String follow(Client client, Client followUser){
      String data="";
      try {
        this.clientBDD.ajouterAbonnement(client.getUsername(), followUser.getUsername());
        data="commande: \n type:'"+this.getNom()+"' \n utilisateur:'"+followUser+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      } catch (Exception e) {
        return "erreur";
    }
    }

}
