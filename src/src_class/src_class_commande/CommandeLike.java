package src_class.src_class_commande;

import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.Server;
import src_class.src_class_modele.Client;

public class CommandeLike implements Commande {

  private String nom;
  private String code;
  private Server serveur;

  /**
   * Constructeur de la classe CommandeLike.
   * 
   * @param serveur   Le serveur où les commandes sont stockées
   */
  public CommandeLike(Server serveur) {
      this.nom = "like";
      this.code = "5";
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
   * Exécute l'action de permettre à un client de liker un message.
   * 
   * @param param    L'ID du message à liker.
   * @param username Le nom d'utilisateur associé à l'exécution de la commande.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   * @throws UnknownHostException Si une erreur liée à une adresse IP inconnue se produit.
   * @throws SQLException          Si une erreur SQL se produit lors de l'exécution de l'action.
   */
  @Override
  public String agis(String param, String username) throws UnknownHostException, SQLException {
      Client clientQuiLike = this.serveur.getClientBDD().getClient(username);
      int idMessage = Integer.parseInt(param);
      return this.like(clientQuiLike, idMessage);
  }

  /**
   * Permet à un client de liker un message.
   * 
   * @param client     Le client qui veut liker le message.
   * @param idMessage  L'identifiant du message à liker.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   */
  public String like(Client client, int idMessage) {
      String data;
      try {
          this.serveur.getMessageBDD().likerMessage(idMessage);
          data = "commande: \n type:'" + this.getNom() + "' \n idMessage:'" + idMessage +
                  "' \n utilisateurCommande='" + client.getUsername() + "'";
          return data;
      } catch (Exception e) {
          return "erreur";
      }
  }
}
