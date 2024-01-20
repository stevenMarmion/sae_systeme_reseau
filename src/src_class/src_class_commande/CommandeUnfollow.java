package src_class.src_class_commande;
import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.Server;
import src_class.src_class_modele.Client;


public class CommandeUnfollow implements Commande {

  private String nom;
  private String code;
  private Server serveur;

  /**
   * Constructeur de la classe CommandeUnfollow.
   * 
   * @param serveur   Le serveur où les commandes sont stockées
   */
  public CommandeUnfollow(Server serveur) {
      this.nom = "unfollow";
      this.code = "4";
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
   * Exécute l'action de permettre à un client de cesser de suivre un autre client.
   * 
   * @param param    Le nom de l'utilisateur à cesser de suivre.
   * @param username Le nom d'utilisateur associé à l'exécution de la commande.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   * @throws UnknownHostException Si une erreur liée à une adresse IP inconnue se produit.
   * @throws SQLException          Si une erreur SQL se produit lors de l'exécution de l'action.
   */
  @Override
  public String agis(String param, String username) throws UnknownHostException, SQLException {
      Client clientExpediteur = this.serveur.getClientBDD().getClient(username);
      Client clientToUnfollow = this.serveur.getClientBDD().getClient(param);
      return this.unfollow(clientExpediteur, clientToUnfollow);
  }

  /**
   * Permet à un client de cesser de suivre un autre client.
   * 
   * @param client         Le client qui souhaite cesser de suivre.
   * @param unfollowUser   Le client qui va être cessé de suivre.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   */
  public String unfollow(Client client, Client unfollowUser) {
      String data;
      try {
          this.serveur.getClientBDD().supprimerAbonnement(client.getUsername(), unfollowUser.getUsername());
          client.supprimeAbonnement(unfollowUser);
          unfollowUser.supprimerAbonnes(client);
          data = "commande: \n type:'" + this.getNom() + "' \n utilisateur:'" + unfollowUser.getUsername() +
                  "' \n utilisateurCommande='" + client.getUsername() + "'";
          return data;
      } catch (Exception e) {
          return "erreur";
      }
  }
}
