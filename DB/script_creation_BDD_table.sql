CREATE TABLE CLIENT (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    ip TEXT NOT NULL,
    INDEX (username)
); -- INDEX permet de pouvoir avoir le référencement sur la table Message

CREATE TABLE MESSAGE (
    id_message INTEGER PRIMARY KEY AUTO_INCREMENT,
    contenu TEXT NOT NULL,
    expediteur VARCHAR(255) NOT NULL,
    date_creation DATETIME NOT NULL,
    nombre_de_like INTEGER DEFAULT 0,
    FOREIGN KEY (expediteur) REFERENCES CLIENT(username)
);

CREATE TABLE ABONNEMENTS (
    subscriber_id INTEGER,
    subscribed_to_id INTEGER,
    PRIMARY KEY (subscriber_id, subscribed_to_id),
    FOREIGN KEY (subscriber_id) REFERENCES CLIENT(id),
    FOREIGN KEY (subscribed_to_id) REFERENCES CLIENT(id)
);
