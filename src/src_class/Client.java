package src_class;
import java.util.ArrayList;

import src_exception.ExceptionFollowUser;

public class Client {
    private String ip;
    private String username;
    private ArrayList<Client> abonnement;
    private ArrayList<Client> abonnes;

    /**
     * Constructeur de la classe Client.
     * @param ip L'adresse IP associée au client.
     * @param username Le nom d'utilisateur du client.
     */
    public Client(String ip, String username) {
        this.ip = ip;
        this.username = username;
        this.abonnement = new ArrayList<>();
        this.abonnes = new ArrayList<>();
    }

    /**
     * Récupère l'adresse IP du client.
     * @return L'adresse IP du client.
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Récupère le nom d'utilisateur du client.
     * @return Le nom d'utilisateur du client.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Récupère la liste des personnes suivies par le client.
     * @return La liste des personnes suivies par le client.
     */
    public ArrayList<Client> getAbonnement() {
        return this.abonnement;
    }

    /**
     * Récupère la liste des abonnés du client.
     * @return La liste des abonnés du client.
     */
    public ArrayList<Client> getAbonnes() {
        return this.abonnes;
    }

    /**
     * Ajoute une personne suivie par le client.
     * @param utilisateur Le client à ajouter aux personnes suivies.
     * @return true si l'ajout a réussi, sinon lance une exception.
     * @throws ExceptionFollowUser si l'utilisateur est déjà suivi par ce client.
     */
    public boolean ajouteAbonnement(Client utilisateur) throws ExceptionFollowUser {
        if (!getAbonnement().contains(utilisateur)) {
            getAbonnement().add(utilisateur);
            return true;
        }
        throw new ExceptionFollowUser(utilisateur);
    }

    /**
     * Ajoute un abonné au client.
     * @param utilisateur Le client à ajouter à la liste des abonnés.
     * @return true si l'ajout a réussi, sinon false.
     */
    public boolean ajouteAbonne(Client utilisateur) {
        if (!getAbonnes().contains(utilisateur)) {
            getAbonnes().add(utilisateur);
            return true;
        }
        return false;
    }

    /**
     * Met à jour l'adresse IP du client.
     * @param newIp La nouvelle adresse IP à définir.
     */
    public void setIp(String newIp) {
        if (!getIp().equals(newIp)) {
            this.ip = newIp;
        }
    }

    /**
     * Met à jour le nom d'utilisateur du client.
     * @param newUsername Le nouveau nom d'utilisateur à définir.
     */
    public void setUsername(String newUsername) {
        if (!getUsername().equals(newUsername)) {
            this.username = newUsername;
        }
    }

    /**
     * Vérifie si deux objets Client sont égaux.
     * @param obj L'objet à comparer.
     * @return true si les objets Client sont égaux, sinon false.
     */
    @Override
    public boolean equals(Object obj) {
        Client autreClient = (Client) obj;
        return (getUsername().equals(autreClient.getUsername()) &&
                getIp().equals(autreClient.getIp()) &&
                getAbonnement().equals(autreClient.getAbonnement()) &&
                getAbonnes().equals(autreClient.getAbonnes()));
    }

    /**
     * Retourne une représentation en chaîne du client.
     * @return Une chaîne représentant l'adresse IP et le nom d'utilisateur du client.
     */
    @Override
    public String toString() {
        return "Client d'ip : " + getIp() + " et de username : " + getUsername();
    }
}
