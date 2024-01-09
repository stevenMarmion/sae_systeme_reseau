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
	}

    public Client getClient(String nomClient) throws SQLException, UnknownHostException {
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CLIENT where nomClient = " + nomClient + ";");
		rs.next();
        Client client = new Client(InetAddress.getByName(rs.getString("adresseIp")), nomClient);
		rs.close();
        client.setAbonnement(this.getAbonnements(nomClient));
        client.setMesMessage(this.getMessagePostes(nomClient));
		return client;
	}

    public ArrayList<Client> getAbonnements(String nomClient) throws SQLException, UnknownHostException {
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CLIENT where nomClient = " + nomClient + ";");
        ArrayList<Client> abonnements = new ArrayList<>();
        while (rs.next()) {
            Client client = new Client(InetAddress.getByName(rs.getString("adresseIp")), rs.getString("abonnements"));
            abonnements.add(client);
        }
        rs.close();
		return abonnements;
	}

    public ArrayList<Message> getMessagePostes(String nomClient) throws SQLException {
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM CLIENT NATURAL JOIN MESSAGE where nomClient = " + nomClient + ";");
		ArrayList<Message> messagesPostes = new ArrayList<>();
        while (rs.next()) {
            int idMessage = rs.getInt("idMessage");
            messagesPostes.add(messageBDD.getMessage(idMessage));
        }
        rs.close();
		return messagesPostes;
	}

}

