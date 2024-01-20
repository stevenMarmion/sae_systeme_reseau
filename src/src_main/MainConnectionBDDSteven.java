package src_main;

import java.sql.SQLException;

import src_class.src_class_modeleBDD.ConnectionBDD;

public class MainConnectionBDDSteven {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionBDD connexion = new ConnectionBDD();
        connexion.connecter("localhost", "reseau_social", "steven", "s07012004");
    }
}
