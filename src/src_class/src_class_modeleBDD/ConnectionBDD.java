package src_class.src_class_modeleBDD;

import java.sql.*;

public class ConnectionBDD {
	private Connection mysql=null;
	private boolean connecte=false;
	public ConnectionBDD() throws ClassNotFoundException{
		this.mysql=null;
		this.connecte=true;
		Class.forName("org.mariadb.jdbc.Driver");
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
		// si tout c'est bien passé la connexion n'est plus nulle
		this.connecte=false;
		this.mysql= null;
		this.mysql= DriverManager.getConnection("jdbc:mysql://"+nomServeur+":3306/"+nomBase, nomLogin, motDePasse); 
		this.connecte=true;
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

