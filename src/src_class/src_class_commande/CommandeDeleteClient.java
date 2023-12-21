package src_class.src_class_commande;

import src_class.Client;

public class CommandeDeleteClient extends Commande{

    public CommandeDeleteClient(String nom, String code){
        super.nom=nom;
        super.code=code;
    }
    /**
     * cette méthode permet de supprimer un message du client localement et sur toute les machines relier au serveur 
     * @param client le client qui veut supprimer sont message 
     * @param idMessage l'id su message a supprimer
     * @return un message qui sera envoyer au serveur pour effectuer la suppresion sur toute les machines
     */
    public String DeleteMessageClient(Client client, int idMessage){
      String data;
      try{
        client.supprimerMessage(idMessage);
        data="commande: \n type:'"+this.getNom()+"' \n idMessage:'"+idMessage+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      }
      catch (Exception e) {return "erreur";}
    }
    
}
