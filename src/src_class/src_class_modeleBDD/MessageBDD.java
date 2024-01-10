package src_class.src_class_modeleBDD;

import java.sql.*;

import src_class.src_class_modele.Message;

public class MessageBDD {
    ConnectionBDD connection;
	Statement st;
	public MessageBDD(ConnectionBDD connection){
		this.connection=connection;
	}

    public Message getMessage(int idMessage) throws SQLException {
		System.out.println(">> MessageBDD.getMessage entre avec le paramètre idMessage " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
        Message message = new Message(this.getContenu(idMessage), 
                                      this.getNomExpediteur(idMessage), 
                                      this.getNBLike(idMessage), 
                                      this.getDate(idMessage));
		rs.close();
		System.out.println(">> MessageBDD.getMessage sort avec " + message);
		return message;
	}

    public String getNomExpediteur(int idMessage) throws SQLException {
		System.out.println(">> MessageBDD.getNomExpediteur entre avec le paramètre idMessage " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE NATURAL JOIN CLIENT where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
        String res = rs.getString("nomClient");
		rs.close();
		System.out.println(">> MessageBDD.getNomExpediteur sort avec " + String.valueOf(res));
		return res;
	}

    public String getContenu(int idMessage) throws SQLException {
		System.out.println(">> MessageBDD.getContenu entre avec le paramètre idMessage " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
		String res = rs.getString("contenu");
		rs.close();
		System.out.println(">> MessageBDD.getContenu sort avec " + String.valueOf(res));
		return res;
	}

    public int getNBLike(int idMessage) throws SQLException {
		System.out.println(">> MessageBDD.getNBLike entre avec le paramètre idMessage " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
		int res = rs.getInt("nombreDeLike");
		rs.close();
		System.out.println(">> MessageBDD.getNBLike sort avec " + String.valueOf(res));
		return res;
	}

    public Date getDate(int idMessage) throws SQLException {
		System.out.println(">> MessageBDD.getDate entre avec le paramètre idMessage " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where idMessage = " + String.valueOf(idMessage) + ";");
		rs.next();
		Date res = rs.getDate("date");
		rs.close();
		System.out.println(">> MessageBDD.getDate sort avec " + String.valueOf(res));
		return res;
	}

	public void ajouterMessage(Message message) throws SQLException {
		System.out.println(">> MessageBDD.ajouterMessage entre avec le paramètre message " + message);
        try {
            PreparedStatement ps = connection.prepareStatement(
                "insert into Message (username, contenu, datePublication, nombreLike) values (?,?,?,?)"
            );
            ps.setString(1, message.getNomExpediteur());
            ps.setString(2, message.getContenu());
            ps.setDate(3, (Date) message.getDate());
            ps.setInt(4, message.getNombreLike());
			System.out.println("<< MessageBDD.ajouterMessage sort avec un ajout fait");
            ps.executeUpdate();
		}
		catch (SQLException e){
			System.out.println("<< MessageBDD.ajouterMessage sort par exeption");
			throw new SQLException("Mauvaise insertion");
		}
    } 

}

