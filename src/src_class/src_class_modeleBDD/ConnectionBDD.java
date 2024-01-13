package src_class.src_class_modeleBDD;

import java.sql.*;

public class ConnectionBDD {
	private Connection mysql=null;
	private boolean connecte=false;
	public ConnectionBDD() throws ClassNotFoundException{
		System.out.println("<< ConnectionBDD.constructor entre");
		this.mysql=null;
		this.connecte=true;
		//Class.forName("org.mariadb.jdbc.Driver");
		System.out.println(">> ConnectionBDD.constructor sort");
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		System.out.println("<< ConnectionBDD.connecter entre avec les paramètres jdbc:mysql://"+nomServeur+":3306/"+nomBase + nomLogin + motDePasse);
		// si tout c'est bien passé la connexion n'est plus nulle
		try {
			this.connecte=false;
			this.mysql= null;
			String url = "jdbc:mysql://"+ nomServeur +":3306/" + nomBase;
			this.mysql= DriverManager.getConnection(url, nomLogin, motDePasse); 
			this.connecte=true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println(">> ConnectionBDD.connecter sort avec la connexion établie : " + this.isConnecte());
	}

	public void close() throws SQLException {
		this.mysql.close();
		this.connecte=false;
	}

    public boolean isConnecte() {
		return this.connecte;
	}
	
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}

