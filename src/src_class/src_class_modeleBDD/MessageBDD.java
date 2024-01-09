package src_class.src_class_modeleBDD;

import java.sql.*;

import src_class.Message;

public class MessageBDD {
    ConnectionBDD connection;
	Statement st;
	public MessageBDD(ConnectionBDD connection){
		this.connection=connection;
	}

    public Message getMessage(int idMessage) throws SQLException {
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
        Message message = new Message(this.getContenu(idMessage), 
                                      this.getNomExpediteur(idMessage), 
                                      this.getNBLike(idMessage), 
                                      this.getDate(idMessage));
		rs.close();
		return message;
	}

    public String getNomExpediteur(int idMessage) throws SQLException {
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE NATURAL JOIN CLIENT where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
        String res = rs.getString("nomClient");
		rs.close();
		return res;
	}

    public String getContenu(int idMessage) throws SQLException {
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
		String res = rs.getString("contenu");
		rs.close();
		return res;
	}

    public int getNBLike(int idMessage) throws SQLException {
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
		int res = rs.getInt("nombreDeLike");
		rs.close();
		return res;
	}

    public Date getDate(int idMessage) throws SQLException {
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
		Date res = rs.getDate("date");
		rs.close();
		return res;
	}

}

