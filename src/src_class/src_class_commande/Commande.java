package src_class.src_class_commande;

import java.net.UnknownHostException;
import java.sql.SQLException;

public interface Commande {

    /**
     * Récupère le nom de la commande.
     * 
     * @return Le nom de la commande.
     */
    public String getNom();

    /**
     * Récupère le code de la commande.
     * 
     * @return Le code de la commande.
     */
    public String getCode();

    /**
     * Exécute l'action associée à la commande en fonction des paramètres fournis.
     * 
     * @param param    Les paramètres nécessaires pour exécuter l'action.
     * @param username Le nom d'utilisateur associé à l'exécution de la commande.
     * @return Une chaîne représentant le résultat de l'action exécutée.
     * @throws UnknownHostException Si une erreur liée à une adresse IP inconnue se produit.
     * @throws SQLException          Si une erreur SQL se produit lors de l'exécution de l'action.
     */
    public String agis(String param, String username) throws UnknownHostException, SQLException;
}
