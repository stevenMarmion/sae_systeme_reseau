package src_class;

import java.util.ArrayList;
import src_exception.ExceptionCommandesAlreadyAdd;
import src_exception.ExceptionIpAlreadyDefined;
import src_exception.ExceptionIpEmpty;
import src_exception.ExceptionIpNonValide;

public class Server {
    private String ipServer;
    private ArrayList<String> commandesServer;

    /**
     * Constructeur pour initialiser un serveur avec une adresse IP et une liste de commandes.
     * @param ip L'adresse IP du serveur.
     * @param commandesServer La liste des commandes du serveur.
     */
    public Server(String ip, ArrayList<String> commandesServer) {
        this.ipServer = ip;
        this.commandesServer = commandesServer;
    }

    /**
     * Constructeur pour initialiser un serveur avec une adresse IP seulement.
     * @param ip L'adresse IP du serveur.
     */
    public Server(String ip) {
        this.ipServer = ip;
        this.commandesServer = new ArrayList<>();
    }

    /**
     * Récupère l'adresse IP du serveur.
     * @return L'adresse IP du serveur.
     */
    public String getIpServer() {
        return this.ipServer;
    } 

    /**
     * Récupère la liste des commandes du serveur.
     * @return La liste des commandes du serveur.
     */
    public ArrayList<String> getCommandesServer() {
        return this.commandesServer;
    }

    /**
     * Définit une nouvelle adresse IP pour le serveur.
     * @param ip La nouvelle adresse IP à définir.
     * @return true si la modification a réussi, sinon lance une exception.
     * @throws ExceptionIpAlreadyDefined si l'IP est déjà définie pour le serveur.
     * @throws ExceptionIpEmpty si l'IP fournie est vide.
     * @throws ExceptionIpNonValide si l'IP fournie est valide.
     */
    public boolean setIpServer(String ip) throws ExceptionIpAlreadyDefined, ExceptionIpEmpty, ExceptionIpNonValide {
        if (ip.isEmpty() || ip==null) {
            throw new ExceptionIpEmpty();
        }
        if (Verificateur.isValidInet4Address(ip)) {
            if (!getIpServer().equals(ip)) {
                this.ipServer = ip;
                return true;
            }
            else {
                throw new ExceptionIpAlreadyDefined(ip);
            }
        }
        else {
            throw new ExceptionIpNonValide(ip); 
        }
    }

    /**
     * Ajoute une nouvelle commande au serveur.
     * @param newCommande La nouvelle commande à ajouter.
     * @return true si l'ajout a réussi, sinon lance une exception.
     * @throws ExceptionCommandesAlreadyAdd si la commande est déjà présente dans la liste.
     */
    public boolean ajouteNouvelleCommandesServer(String newCommande) throws ExceptionCommandesAlreadyAdd {
        if (!getCommandesServer().contains(newCommande)) {
            getCommandesServer().add(newCommande);
            return true;
        }
        throw new ExceptionCommandesAlreadyAdd(newCommande);
    }

    /**
     * Ajoute une liste de commandes au serveur.
     * @param listeCommandesServer La liste des commandes à ajouter.
     * @return true si l'ajout a réussi, sinon lance une exception.
     * @throws ExceptionCommandesAlreadyAdd si une commande de la liste est déjà présente dans la liste du serveur.
     */
    public boolean ajouteListCommandes(ArrayList<String> listeCommandesServer) throws ExceptionCommandesAlreadyAdd {
        if (getCommandesServer().isEmpty()) {
            this.commandesServer = listeCommandesServer;
            return true;
        }
        else if (!getCommandesServer().isEmpty()){
            for (String commande : listeCommandesServer) {
                if (!getCommandesServer().contains(commande)) {
                    ajouteNouvelleCommandesServer(commande);
                }
                else {
                    throw new ExceptionCommandesAlreadyAdd(commande);
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Retourne une représentation en chaîne du serveur.
     * @return Une chaîne représentant l'adresse IP et la liste des commandes du serveur.
     */
    @Override
    public String toString() {
        return "Serveur défini par l'IP : " + getIpServer() + ", liste des commandes : " + getCommandesServer();
    }
}
