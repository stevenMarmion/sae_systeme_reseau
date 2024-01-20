package src_class.src_class_modeleBDD;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

import src_class.src_class_modele.Client;
import src_class.src_class_modele.Message;

public class ClientBDD {
    ConnectionBDD connection;
    MessageBDD messageBDD;
	Statement st;
	public ClientBDD(ConnectionBDD connection){
		this.connection=connection;
        this.messageBDD = new MessageBDD(connection);
	}

    public Client getClient(String nomClient) throws SQLException, UnknownHostException {
        try {
            System.out.println("<< ClientBDD.getClient entre avec le paramètre client " + nomClient);
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CLIENT where username = '" + nomClient + "';");
            rs.next();
            Client client = new Client(InetAddress.getByName(rs.getString("ip")), nomClient);
            rs.close();
            client.setAbonnement(this.getAbonnements(nomClient));
            client.setMesMessage(this.getMessagePostes(nomClient));
            System.out.println(">> ClientBDD.getClient sort avec le client : " + client);
            return client;
        } catch (SQLException e) {
            System.out.println(">> ClientBDD.getClient sort en exeption");
            e.printStackTrace();
        }
        return null;
	}

    public Client getClientById(int idClient) throws SQLException, UnknownHostException {
        try {
            System.out.println("<< ClientBDD.getClientById entre avec le paramètre client " + String.valueOf(idClient));
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CLIENT where id_client = '" + String.valueOf(idClient) + "';");
            rs.next();
            Client client = new Client(InetAddress.getByName(rs.getString("ip")), String.valueOf(idClient));
            rs.close();
            client.setAbonnement(this.getAbonnements(client.getUsername()));
            client.setMesMessage(this.getMessagePostes(client.getUsername()));
            System.out.println(">> ClientBDD.getClientById sort avec le client : " + client);
            return client;
        } catch (SQLException e) {
            System.out.println(">> ClientBDD.getClientById sort en exeption");
            e.printStackTrace();
        }
        return null;
	}

    public Client chargeInfos(InetAddress ip, String username) throws SQLException, UnknownHostException {
        System.out.println(">> ClientBDD.chargeInfos entre avec les paramètres client " + String.valueOf(ip) + " et le nom utilisateur " + username);
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CLIENT where username = '" + username + "';");
		rs.next();
        Client client = new Client(ip, username);
		rs.close();
        client.setAbonnement(this.getAbonnements(username));
        client.setMesMessage(this.getMessagePostes(username));
        System.out.println("<< ClientBDD.chargeInfos a chargé les infos client : " + client);
		return client;
	}

    public boolean estClientExistant(String username) throws SQLException, UnknownHostException {
        try {
            System.out.println(">> ClientBDD.estClientExistant entre avec les paramètres client " + username);
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CLIENT where username = '" + username + "';");
            if (!rs.next()) {
                System.out.println("<< ClientBDD.estClientExistant sort avec un client inconnu");
                return false;
            }
            System.out.println("<< ClientBDD.estClientExistant sort avec un client existant");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

    public ArrayList<Client> getAbonnements(String nomClient) throws SQLException, UnknownHostException {
        try {
            System.out.println(">> ClientBDD.getAbonnements entre avec le paramètres username " + nomClient);
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT c1.id AS UTILISATEUR_ID, " +
                                            "c1.username AS UTILISATEUR_USERNAME, " + 
                                            "c1.ip AS UTILISATEUR_IP, " + 
                                            "c2.id AS ABONNEMENT_ID, " +
                                            "c2.username AS ABONNEMENT_USERNAME, " +
                                            "c2.ip AS ABONNEMENT_IP " +
                                            "FROM " +
                                            "CLIENT c1 JOIN " +
                                            "ABONNEMENTS a ON c1.ID = a.subscribed_to_id JOIN " +
                                            "CLIENT c2 ON a.subscribed_to_id = c2.id WHERE " +
                                            "c1.username = '" + nomClient + "';");
            ArrayList<Client> abonnements = new ArrayList<>();
            rs.next();
            while (rs.next()) {
                Client client = this.getClient(rs.getString("ABONNEMENT_USERNAME"));
                abonnements.add(client);
            }
            rs.close();
            System.out.println("<< ClientBDD.getAbonnements sort avec une liste d'abonnements de taille " + String.valueOf(abonnements.size()));
            return abonnements;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

    public ArrayList<Message> getMessagePostes(String nomClient) throws SQLException {
        System.out.println(">> ClientBDD.getMessagePostes entre avec les paramètres client " + nomClient);
        try {
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT m.id_message, " +
                                            "m.contenu, " +
                                            "m.expediteur, " +
                                            "m.date_creation, " +
                                            "m.nombre_de_like " +
                                            "FROM " +
                                            "MESSAGE m " +
                                            "JOIN " +
                                            "CLIENT c ON m.expediteur = c.username " +
                                            "WHERE " +
                                            "c.username = '" + nomClient + "';");
            ArrayList<Message> messagesPostes = new ArrayList<>();
            while (rs.next()) {
                int idMessage = rs.getInt("id_message");
                messagesPostes.add(messageBDD.getMessage(idMessage));
            }
            rs.close();
            System.out.println("<< ClientBDD.getMessagePostes sort avec une liste de messages postés de taille " + String.valueOf(messagesPostes.size()));
            return messagesPostes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
	}

    public int getNombreAbonnements(String nomClient) throws SQLException {
        try {
            System.out.println(">> ClientBDD.getNombreAbonnements entre avec le paramètre username " + nomClient);
            st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS nombreAbonnements " +
                                          "FROM ABONNEMENTS a " +
                                          "JOIN CLIENT c ON a.subscriber_id = c.id " +
                                          "WHERE c.username = '" + nomClient + "';");
    
            int nombreAbonnements = 0;
            if (rs.next()) {
                nombreAbonnements = rs.getInt("nombreAbonnements");
            }
    
            rs.close();
            System.out.println("<< ClientBDD.getNombreAbonnements sort avec le nombre d'abonnements " + nombreAbonnements);
            return nombreAbonnements;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNombreAbonnes(String nomClient) throws SQLException {
        try {
            System.out.println(">> ClientBDD.getNombreAbonnes entre avec le paramètre username " + nomClient);
            st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) AS nombreAbonnes " +
                                          "FROM ABONNEMENTS a " +
                                          "JOIN CLIENT c ON a.subscribed_to_id = c.id " +
                                          "WHERE c.username = '" + nomClient + "';");
    
            int nombreAbonnes = 0;
            if (rs.next()) {
                nombreAbonnes = rs.getInt("nombreAbonnes");
            }
    
            rs.close();
            System.out.println("<< ClientBDD.getNombreAbonnes sort avec le nombre d'abonnés " + nombreAbonnes);
            return nombreAbonnes;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    

    public void ajouterClient(String username, InetAddress ip) throws SQLException {
        System.out.println(">> ClientBDD.ajouterClient entre avec les paramètres client " + username + " et l'adresse ip " + String.valueOf(ip));
        try {
            PreparedStatement ps = connection.prepareStatement(
                "insert into CLIENT (username, ip) values (?,?)"
            );
            ps.setString(1, username);
            ps.setString(2, String.valueOf(ip).substring(1, String.valueOf(ip).length()));
            ps.executeUpdate();
            System.out.println("<< ClientBDD.ajouterClient sort avec un client ajouté");
		}
		catch (SQLException e){
            e.printStackTrace();
            System.out.println("<< ClientBDD.ajouterClient sort en exeption");
			throw new SQLException("Mauvaise insertion");
		}
    } 

    public void ajouterAbonnement(String utilisateurQuiSAbonne, String utilisateurSuivi) throws SQLException {
        System.out.println(">> ClientBDD.ajouterAbonnement entre avec les paramètres utilisateur qui s'abonne : " + utilisateurQuiSAbonne + ", utilisateur suivi : " + utilisateurSuivi);
        try {
            int idUtilisateurQuiSAbonne = getIdUtilisateurParUsername(utilisateurQuiSAbonne);
            int idUtilisateurSuivi = getIdUtilisateurParUsername(utilisateurSuivi);
            if (idUtilisateurQuiSAbonne == idUtilisateurSuivi) {
                System.out.println("<< ClientBDD.ajouterAbonnement sort avec une erreur - Vous essayez de vous suivre vous même");
            }
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("select * from ABONNEMENTS where subscriber_id = " + idUtilisateurQuiSAbonne + " and subscribed_to_id = " + idUtilisateurSuivi + ";");
            if (rs.next()) {
                System.out.println("<< ClientBDD.ajouterAbonnement sort avec une erreur - Vous suivez déjà cet utilisateur");
            }
            else if (idUtilisateurQuiSAbonne != -1 && idUtilisateurSuivi != -1) {
                PreparedStatement ps = connection.prepareStatement(
                        "INSERT INTO ABONNEMENTS (subscriber_id, subscribed_to_id) VALUES (?, ?)"
                );
                ps.setInt(1, idUtilisateurQuiSAbonne);
                ps.setInt(2, idUtilisateurSuivi);
                ps.executeUpdate();
                System.out.println("<< ClientBDD.ajouterAbonnement sort avec un abonnement ajouté");
            } else {
                System.out.println("<< ClientBDD.ajouterAbonnement sort avec une erreur - Utilisateur non trouvé");
                throw new SQLException("Utilisateur non trouvé");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("<< ClientBDD.ajouterAbonnement sort en exception");
            throw new SQLException("Mauvaise insertion");
        }
    }

    public int getIdUtilisateurParUsername(String username) throws SQLException {
        System.out.println(">> ClientBDD.getIdUtilisateurParUsername entre avec le parametre " + username);
        try {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT id FROM CLIENT WHERE username = ?"
            );
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRes= rs.getInt("id");
                System.out.println(">> ClientBDD.getIdUtilisateurParUsername sort avec un id trouvé");
                return idRes;
            } 
            else {
                System.out.println(">> ClientBDD.getIdUtilisateurParUsername sort avec un id non trouvé");
                return -1;
            }
        }
        catch (SQLException e) {
            System.out.println(">> ClientBDD.getIdUtilisateurParUsername sort en exception");
            e.printStackTrace();
        }
        return -1;
    }

    public String supprimerUtilisateur(String username) throws SQLException {
        System.out.println(">> ClientBDD.supprimerUtilisateur entre avec le paramètre username : " + username);
        try {
            int idUtilisateur = getIdUtilisateurParUsername(username);
            if (idUtilisateur != -1) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "DELETE FROM CLIENT WHERE id = ?"
                )) {
                    ps.setInt(1, idUtilisateur);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("<< ClientBDD.supprimerUtilisateur sort avec l'utilisateur supprimé");
                        return username;
                    } else {
                        System.out.println("<< ClientBDD.supprimerUtilisateur sort avec une erreur - Aucune ligne supprimée");
                        throw new SQLException("Aucune ligne supprimée");
                    }
                }
            } else {
                System.out.println("<< ClientBDD.supprimerUtilisateur sort avec une erreur - Utilisateur non trouvé");
                throw new SQLException("Utilisateur non trouvé");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("<< ClientBDD.supprimerUtilisateur sort en exception");
            throw new SQLException("Mauvaise suppression");
        }
    }

    public void supprimerAbonnement(String utilisateurQuiSDesAbonne, String utilisateurSuivi) throws SQLException {
        System.out.println(">> ClientBDD.supprimerAbonnement entre avec les paramètres utilisateur qui se désabonne : "
                + utilisateurQuiSDesAbonne + ", utilisateur suivi : " + utilisateurSuivi);
        try {
            int idUtilisateurQuiSDesAbonne = getIdUtilisateurParUsername(utilisateurQuiSDesAbonne);
            int idUtilisateurSuivi = getIdUtilisateurParUsername(utilisateurSuivi);
            st=connection.createStatement();
            ResultSet rs = st.executeQuery("select * from ABONNEMENTS where subscriber_id = " + idUtilisateurQuiSDesAbonne + " and subscribed_to_id = " + idUtilisateurSuivi + ";");
            if (idUtilisateurQuiSDesAbonne == idUtilisateurSuivi) {
                System.out.println("<< ClientBDD.supprimerAbonnement sort avec une erreur - Vous essayez de vous désabonnez de vous même");
            }
            else if (!rs.next()) {
                System.out.println("<< ClientBDD.supprimerAbonnement sort avec une erreur - Vous vous êtes déjà désabonnez de cet utilisateur");
            }
            else if (idUtilisateurQuiSDesAbonne != -1 && idUtilisateurSuivi != -1) {
                try (PreparedStatement ps = connection.prepareStatement(
                        "DELETE FROM ABONNEMENTS WHERE subscriber_id = ? AND subscribed_to_id = ?"
                )) {
                    ps.setInt(1, idUtilisateurQuiSDesAbonne);
                    ps.setInt(2, idUtilisateurSuivi);
                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("<< ClientBDD.supprimerAbonnement sort avec l'abonnement supprimé");
                    } else {
                        System.out.println("<< ClientBDD.supprimerAbonnement sort avec une erreur - Aucune ligne supprimée");
                        throw new SQLException("Aucune ligne supprimée");
                    }
                }
            } else {
                System.out.println("<< ClientBDD.supprimerAbonnement sort avec une erreur - Utilisateur non trouvé");
                throw new SQLException("Utilisateur non trouvé");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("<< ClientBDD.supprimerAbonnement sort en exception");
            throw new SQLException("Mauvaise suppression");
        }
    }
    
}

