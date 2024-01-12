package src_class.src_class_commande;

import src_class.src_class_modele.Client;
import src_class.Server;

public class CommandeDeleteClient implements Commande {

  private String nom;
  private String code;
  private Server serveur;

  /**
   * Constructeur de la classe CommandeDeleteClient.
   * 
   * @param serveur   Le serveur où les commandes sont stockées
   */
  public CommandeDeleteClient(Server serveur) {
      this.nom = "delete";
      this.code = "6";
      this.serveur = serveur;
  }

  /**
   * Récupère le nom de la commande.
   * 
   * @return Le nom de la commande.
   */
  @Override
  public String getNom() {
      return this.nom;
  }

  /**
   * Récupère le code de la commande.
   * 
   * @return Le code de la commande.
   */
  @Override
  public String getCode() {
      return this.code;
  }

  /**
   * Exécute l'action de suppression d'un message associé à un client localement et sur toutes les machines.
   * 
   * @param param    L'ID du message à supprimer.
   * @param username Le nom d'utilisateur associé à l'exécution de la commande.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   */
  @Override
  public String agis(String param, String username) {
      try {
          Integer idMessage = Integer.parseInt(param);
          Client clientWithUsername = this.serveur.getClientBDD().getClient(username);
          return this.deleteMessageClient(clientWithUsername, idMessage);
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
  }

  /**
   * Supprime un message du client localement et sur toutes les machines connectées au serveur.
   * 
   * @param client     Le client qui souhaite supprimer son message.
   * @param idMessage  L'ID du message à supprimer.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   */
  public String deleteMessageClient(Client client, int idMessage) {
      try {
          this.serveur.getMessageBDD().effacerMessage(idMessage);
          String data = "commande: \n type:'" + this.getNom() + "' \n idMessage:'" + idMessage + "' \n utilisateurCommande='" + client.getUsername() + "'";
          return data;
      } catch (Exception e) {
          return null;
      }
  }
}
