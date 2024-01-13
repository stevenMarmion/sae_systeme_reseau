package src_class.src_class_modeleBDD;

import java.net.UnknownHostException;
import java.sql.*;
import java.util.ArrayList;

import src_class.src_class_modele.Client;


public class AbonnementsBDD {
    ConnectionBDD connection;
	Statement st;
	ClientBDD clientBDD;
	public AbonnementsBDD(ConnectionBDD connection){
		this.connection=connection;
		this.clientBDD = new ClientBDD(connection);
	}

    public ArrayList<Client> getAbonnementsByID(int id_client) throws SQLException, UnknownHostException {
		System.out.println(">> AbonnementsBDD.getAbonnementsByID entre avec le param id_client " + String.valueOf(id_client));
		st=connection.createStatement();
		ArrayList<Client> abonnements = new ArrayList<>();
		ResultSet rs = st.executeQuery("select * from ABONNEMENTS where subscriber_id = " + String.valueOf(id_client));
        while (rs.next()) {
			abonnements.add(clientBDD.getClientById(id_client));
		}
		System.out.println(">> AbonnementsBDD.getAbonnementsByID sort avec une liste d'abonnements de taille " + abonnements.size());
		return abonnements;
	}

	public ArrayList<Client> getAbonnesByID(int id_client) throws SQLException, UnknownHostException {
		System.out.println(">> AbonnementsBDD.getAbonnesByID entre avec le param id_client " + String.valueOf(id_client));
		st=connection.createStatement();
		ArrayList<Client> abonnements = new ArrayList<>();
		ResultSet rs = st.executeQuery("select * from ABONNEMENTS where subscribed_to_id = " + String.valueOf(id_client));
        while (rs.next()) {
			abonnements.add(clientBDD.getClientById(id_client));
		}
		System.out.println(">> AbonnementsBDD.getAbonnesByID sort avec une liste d'abonnements de taille " + abonnements.size());
		return abonnements;
	}
    
}
