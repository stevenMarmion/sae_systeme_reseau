package src_class.src_class_commande;

import src_class.src_class_modele.Client;
import src_class.src_class_modeleBDD.*;

public class CommandeDeleteClient implements Commande{

    private String nom;
    private String code;
    private MessageBDD messageBDD;
    private ClientBDD clientBDD;

    public CommandeDeleteClient(String nom, String code, MessageBDD messageBDD, ClientBDD clientBDD){
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
        return this.deleteMessageClient(clientWithUsername, idMessage);
      } catch (Exception e) {
        
      }
      return null;
    }

    /**
     * cette méthode permet de supprimer un message du client localement et sur toute les machines relier au serveur 
     * @param client le client qui veut supprimer sont message 
     * @param idMessage l'id su message a supprimer
     * @return un message qui sera envoyer au serveur pour effectuer la suppresion sur toute les machines
     */
    public String deleteMessageClient(Client client, int idMessage){
      String data;
      try{
        this.messageBDD.effacerMessage(idMessage);
        data="commande: \n type:'"+this.getNom()+"' \n idMessage:'"+idMessage+"' \n utilisateurCommande='"+client.getUsername()+"'";
        return data;
      }
      catch (Exception e) {return null;}
    }
    

}
