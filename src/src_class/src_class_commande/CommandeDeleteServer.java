package src_class.src_class_commande;

import src_class.Server;
import src_class.src_class_modele.Client;

public class CommandeDeleteServer implements Commande {

  private String nom;
  private String code;
  private Server serveur;

  /**
   * Constructeur de la classe CommandeDeleteServer.
   * 
   * @param serveur   Le serveur où les commandes sont stockées
   */
  public CommandeDeleteServer(Server serveur) {
      this.nom = "delete";
      this.code = "1";
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
   * Exécute l'action de suppression d'un message associé à un client localement par le serveur.
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
          return this.deleteMessageByServeur(clientWithUsername, idMessage);
      } catch (Exception e) {
          // Gérer l'exception, éventuellement journaliser l'erreur.
      }
      return null;
  }

  /**
   * Supprime un message du client localement par le serveur.
   * 
   * @param client     Le client dont le message doit être supprimé.
   * @param idMessage  L'ID du message à supprimer.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   */
  public String deleteMessageByServeur(Client client, int idMessage) {
      try {
          this.serveur.getMessageBDD().effacerMessage(idMessage, client.getUsername());
          String data = "commande: \n type:'" + this.getNom() + "' \n idMessage:'" + idMessage +
                  "' \n utilisateurCommande = Serveur";
          return data;
      } catch (Exception e) {
          return null;
      }
  }
}