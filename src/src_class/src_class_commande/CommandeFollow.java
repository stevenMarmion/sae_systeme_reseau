package src_class.src_class_commande;
import java.net.UnknownHostException;
import java.sql.SQLException;

import src_class.Server;
import src_class.src_class_modele.Client;

public class CommandeFollow implements Commande {

  private String nom;
  private String code;
  private Server serveur;

  /**
   * Constructeur de la classe CommandeFollow.
   * 
   * @param serveur   Le serveur où les commandes sont stockées
   */
  public CommandeFollow(Server serveur) {
      this.nom = "follow";
      this.code = "3";
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
   * Exécute l'action de permettre à un client de suivre un autre client.
   * 
   * @param param    Le nom du client à suivre.
   * @param username Le nom d'utilisateur associé à l'exécution de la commande.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   * @throws UnknownHostException Si une erreur liée à une adresse IP inconnue se produit.
   * @throws SQLException          Si une erreur SQL se produit lors de l'exécution de l'action.
   */
  @Override
  public String agis(String param, String username) throws UnknownHostException, SQLException {
      Client clientExpediteur = this.serveur.getClientBDD().getClient(username);
      Client clientToFollow = this.serveur.getClientBDD().getClient(param);
      return this.follow(clientExpediteur, clientToFollow);
  }

  /**
   * Permet à un client de suivre un autre client.
   * 
   * @param client      Le client qui souhaite suivre.
   * @param followUser  Le client qui va être suivi.
   * @return Une chaîne représentant les détails de la commande à envoyer au serveur.
   */
  public String follow(Client client, Client followUser) {
      String data = "";
      try {
          this.serveur.getClientBDD().ajouterAbonnement(client.getUsername(), followUser.getUsername());
          client.ajouteAbonnement(followUser);
          followUser.ajouteAbonne(client);
          System.out.println(client.getAbonnement());
          System.out.println(followUser.getAbonnes());
          data = "commande: \n type:'" + this.getNom() + "' \n utilisateur:'" + followUser.getUsername() +
                  "' \n utilisateurCommande='" + client.getUsername() + "'";
          return data;
      } catch (Exception e) {
          return "erreur";
      }
  }
}
