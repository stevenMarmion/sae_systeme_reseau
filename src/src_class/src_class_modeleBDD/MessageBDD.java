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
		System.out.println(">> MessageBDD.getMessage entre avec le paramètre id_message " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where id_message = " + String.valueOf(idMessage) + ";");
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
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where id_message = " + String.valueOf(idMessage) + ";");
		rs.next();
        String res = rs.getString("expediteur");
		rs.close();
		System.out.println(">> MessageBDD.getNomExpediteur sort avec " + String.valueOf(res));
		return res;
	}

    public String getContenu(int idMessage) throws SQLException {
		System.out.println(">> MessageBDD.getContenu entre avec le paramètre idMessage " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where id_message = " + String.valueOf(idMessage) + ";");
		rs.next();
		String res = rs.getString("contenu");
		rs.close();
		System.out.println(">> MessageBDD.getContenu sort avec " + String.valueOf(res));
		return res;
	}

    public int getNBLike(int idMessage) throws SQLException {
		System.out.println(">> MessageBDD.getNBLike entre avec le paramètre idMessage " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where id_message = " + String.valueOf(idMessage) + ";");
		rs.next();
		int res = rs.getInt("nombre_de_like");
		rs.close();
		System.out.println(">> MessageBDD.getNBLike sort avec " + String.valueOf(res));
		return res;
	}

    public Timestamp getDate(int idMessage) throws SQLException {
		System.out.println(">> MessageBDD.getDate entre avec le paramètre idMessage " + String.valueOf(idMessage));
		st=connection.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM MESSAGE where id_message = " + String.valueOf(idMessage) + ";");
		rs.next();
		Timestamp res = rs.getTimestamp("date_creation");
		rs.close();
		System.out.println(">> MessageBDD.getDate sort avec " + String.valueOf(res));
		return res;
	}

	public void ajouterMessage(Message message) throws SQLException {
		System.out.println(">> MessageBDD.ajouterMessage entre avec le paramètre message " + message);
        try {
            PreparedStatement ps = connection.prepareStatement(
                "insert into MESSAGE (contenu, expediteur, date_creation, nombre_de_like) values (?,?,?,?)"
            );
            ps.setString(1, message.getContenu());
			ps.setString(2, message.getNomExpediteur());
			System.out.println("avant date");
            ps.setTimestamp(3, message.getDate());
			System.out.println("apres date");
            ps.setInt(4, message.getNombreLike());
            ps.executeUpdate();
			System.out.println("<< MessageBDD.ajouterMessage sort avec un ajout fait");
		}
		catch (SQLException e){
			System.out.println("<< MessageBDD.ajouterMessage sort par exeption");
			e.printStackTrace();
			throw new SQLException("Mauvaise insertion");
		}
    } 

	public void effacerMessage(int idMessage, String expediteur) throws SQLException {
		System.out.println(">> MessageBDD.effacerMessage entre avec le paramètre idMessage " + idMessage);
		st = connection.createStatement();
		try {
			st.executeQuery("delete from MESSAGE where id_message = " + String.valueOf(idMessage) + " and expediteur = '" + expediteur + "';");
			System.out.println("<< MessageBDD.effacerMessage sort avec le message " + idMessage + " supprimé");
		}
		catch (SQLException e){
			System.out.println("<< MessageBDD.effacerMessage sort par exeption");
			throw new SQLException("Message inexistant dans la table");
		}
		st.close();
	}

	public void likerMessage(int idMessage) throws SQLException {
		System.out.println(">> ClientBDD.likerMessage entre avec le paramètre idMessage : " + idMessage);
		try {
			PreparedStatement ps = connection.prepareStatement(
					"UPDATE MESSAGE SET nombre_de_like = nombre_de_like + 1 WHERE id_message = ?"
			);
			ps.setInt(1, idMessage);
			int isLiked = ps.executeUpdate();
			if (isLiked > 0) {
				System.out.println("<< ClientBDD.likerMessage sort avec le message liké");
			} else {
				System.out.println("<< ClientBDD.likerMessage sort avec une erreur - Message non trouvé");
				throw new SQLException("Message non trouvé");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("<< ClientBDD.likerMessage sort en exception");
			throw new SQLException("Mauvaise mise à jour");
		}
	}
	public void supprimerMessagesUtilisateur(String username) throws SQLException {
        System.out.println(">> ClientBDD.supprimerMessagesUtilisateur entre avec le paramètre username : " + username);
		try (PreparedStatement ps = connection.prepareStatement(
				"DELETE FROM MESSAGE WHERE expediteur = ?"
		)) {
			ps.setString(1, username);
			int rowsAffected = ps.executeUpdate();

			System.out.println("<< ClientBDD.supprimerMessagesUtilisateur sort avec "
					+ rowsAffected + " message(s) de l'utilisateur supprimé(s)");
		} catch (SQLException e) {
            e.printStackTrace();
            System.out.println("<< ClientBDD.supprimerMessagesUtilisateur sort en exception");
            throw new SQLException("Mauvaise suppression");
        }
    }

	public String getMessageAbonnements(String username) throws SQLException{
		System.out.println(">> ClientBDD.getMessageAbonnments entre avec le paramètre username : " + username);
		try {
			PreparedStatement ps = connection.prepareStatement("select * from MESSAGE where expediteur in (select username from client where id in (select subscribed_to_id from ABONNEMENTS where subscriber_id in (select id from client where username = ?))) order by date_creation desc limit 20");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			String mes="";
			while (rs.next()) {
				String contenu = rs.getString("contenu");
				String expediteur = rs.getString("expediteur");
				String bord_h = "=".repeat(52)+"||newline||";
				mes+=" ".repeat(25)+expediteur+"||newline||";
				mes+=bord_h;
				while(contenu.length()>0){
					if(contenu.length()>50){
						mes+="|"+contenu.substring(0,50)+"| ||newline||";
						contenu=contenu.substring(50);
					}
					else{
						mes+="|"+contenu+" ".repeat(50-contenu.length())+"| ||newline||";
						contenu="";
					}
				}
				mes+="=".repeat(52)+"||newline||";
				mes+="||newline||";
				mes+="nombre de like : "+rs.getString("nombre_de_like")+"||newline||";
				mes+="id : "+rs.getString("id_message")+"||newline||";
				mes+="date : "+rs.getString("date_creation")+"||newline||";
				mes+="||newline||";
						}
			return mes;
		} catch (Exception e) {
			// TODO: handle exception
			return "erreur";
		}
	}

}

