package src_class.src_class_commande;
import src_class.Client;


public class CommandeFollow extends Commande{
    public CommandeFollow(){
        super.nom="follow";
        super.code="2";
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
        client.ajouteAbonnement(followUser);
        data="commande: \n type:'"+this.getNom()+"' \n utilisateur:'"+followUser+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      } catch (Exception e) {
        return "erreur";
    }
    }

}
