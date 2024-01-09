package src_test;

import java.sql.SQLException;

import src_class.src_class_modeleBDD.ConnectionBDD;

public class MainConnectionBDD {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionBDD connexion = new ConnectionBDD();
        connexion.connecter("localhost", "reseau_social", "steven", "s07012004");
        System.out.println(connexion.isConnecte());
    }
}
