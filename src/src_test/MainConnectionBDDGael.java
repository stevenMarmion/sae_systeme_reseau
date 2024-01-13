package src_test;

import java.sql.SQLException;

import src_class.src_class_modeleBDD.ConnectionBDD;

public class MainConnectionBDDGael {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionBDD connexion = new ConnectionBDD();
        connexion.connecter("localhost", "reseau_social", "root", "simon");
    }
}
