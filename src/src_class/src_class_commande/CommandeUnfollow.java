package src_class.src_class_commande;
import src_class.Client;

public class CommandeUnfollow extends Commande{
    public CommandeUnfollow(){
        super.nom="unfollow";
        super.code="1";
    }
    /**
     * permet de unFollow un utilisateur 
     * @param client le client qui veut unfollow
     * @param unfollowUser le nom de l'utilisateur qui sera unfollow
     * @return un message sui sera envoyer au serveur pour qu'il puissent actualiser la liste des abonnes de l'utilisateur
     */
    public String unfollow(Client client, String unfollowUser){
      String data;
      try{
        client.supprimeAbonnement(unfollowUser);
        data="commande: \n type:'"+this.getNom()+"' \n utilisateur:'"+unfollowUser+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      }
      catch (Exception e) {return "erreur";}
    }
    

}
