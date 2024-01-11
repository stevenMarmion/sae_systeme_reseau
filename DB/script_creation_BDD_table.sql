CREATE TABLE Client (
    id INTEGER PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    ip TEXT NOT NULL,
    INDEX (username)
); -- INDEX permet de pouvoir avoir le référencement sur la table Message

CREATE TABLE Message (
    id_message INTEGER PRIMARY KEY,
    contenu TEXT NOT NULL,
    expediteur VARCHAR(255) NOT NULL,
    date_creation DATETIME NOT NULL,
    nombre_de_like INTEGER DEFAULT 0,
    FOREIGN KEY (expediteur) REFERENCES Client(username)
);

CREATE TABLE Abonnements (
    subscriber_id INTEGER,
    subscribed_to_id INTEGER,
    PRIMARY KEY (subscriber_id, subscribed_to_id),
    FOREIGN KEY (subscriber_id) REFERENCES Client(id),
    FOREIGN KEY (subscribed_to_id) REFERENCES Client(id)
);
