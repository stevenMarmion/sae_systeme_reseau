package src_class.src_class_commande;

import src_class.src_class_modele.Client;

public class CommandeLike extends Commande{
    public CommandeLike(String nom,String code){
        super.nom=nom;
        super.code=code;
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
