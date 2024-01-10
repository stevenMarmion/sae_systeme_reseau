package src_class.src_class_commande;
import src_class.src_class_modele.Client;

public class CommandeUnfollow implements Commande{

    private String nom;
    private String code;

    public CommandeUnfollow(){
        this.nom="unfollow";
        this.code="4";
    }

    @Override
    public String getNom() {
      return this.nom;
    }

    @Override
    public String getCode() {
      return this.code;
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
        client.supprimeAbonnement(unfollowUser);
        data="commande: \n type:'"+this.getNom()+"' \n utilisateur:'"+unfollowUser+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      }
      catch (Exception e) {return "erreur";}
    }
    

}
