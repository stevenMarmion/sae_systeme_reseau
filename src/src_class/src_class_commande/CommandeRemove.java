package src_class.src_class_commande;

import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.Server;

public class CommandeRemove implements Commande {

  private String nom;
  private String code;
  private Server serveur;

  /**
   * Constructeur de la classe CommandeRemove.
   * 
   * @param serveur   Le serveur où les commandes sont stockées
   */
  public CommandeRemove(Server serveur) {
      this.nom = "remove";
      this.code = "2";
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
   * Exécute l'action de supprimer un utilisateur et tous ses messages associés.
   * 
   * @param param    Le nom d'utilisateur à supprimer.
   * @param username Le nom d'utilisateur associé à l'exécution de la commande (Serveur).
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   * @throws UnknownHostException Si une erreur liée à une adresse IP inconnue se produit.
   * @throws SQLException          Si une erreur SQL se produit lors de l'exécution de l'action.
   */
  @Override
  public String agis(String param, String username) throws UnknownHostException, SQLException {
      return this.remove(param);
  }

  /**
   * Supprime un utilisateur et tous ses messages associés.
   * 
   * @param usernameToDelete Le nom d'utilisateur à supprimer.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   */
  public String remove(String usernameToDelete) {
      String data;
      try {
          this.serveur.getClientBDD().supprimerUtilisateur(usernameToDelete);
          this.serveur.getMessageBDD().supprimerMessagesUtilisateur(usernameToDelete);
          data = "commande: \n type:'" + this.getNom() + "' \n username:'" + usernameToDelete +
                  "' \n utilisateurCommande = Serveur";
          return data;
      } catch (Exception e) {
          return "erreur";
      }
  }
}

