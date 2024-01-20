# **SAE SYSTEME/RESEAU**

## Membres

Marmion Steven
SIMON Gael

## Contexte

Développer une application client-serveur d’un réseau social : les utilisateurs peuvent publier des messages, et suivre d’autres utilisateurs pour consulter leurs publications.

## Projet

L'application devra fonctions sous deux aspects :

- le client qui doit se connecter au serveur pour afficher les messages des personnes qu’il suit et poster ses propres publications,
- le serveur qui reçoit les messages, et les réexpédie aux bons utilisateurs.

## Rendus

### Rendu intermédiaire le 22/12/2023

- Diagramme de classes
- Une archive avec un prototype de l’application : un client et un serveur qui peuvent communiquer avec envois de messages simples (pas de commandes)

### Rendu final Le 20/01/2024

- Diagramme des classes final
- Application fonctionnelle
- Rapport : Manuel d’utilisation et justification des choix techniques
- Documentation (javadoc)

# IMPORTANT

Il faut modifier le fichier `src/src_class/Server.java` a la ligne 56 `this.connectionBDD.connecter("localhost", "reseau_social", "root", "simon");`et mettre la database que vous souhaiter utiliser en second parametre, le nom d'utilisateur de la database en troisieme parametre et le mot de passe en quatrieme parametre.

Avoir le driver `mariadb-java-client.jar` dans votre librairie.

Pour lancer le serveur via VSCODE il faut juste lancer (avce la fleche en haut a droite ) le `src\src_main\MainTestServeur.java` pour le serveur et pour les Clients `src\src_main\MainTest1Client.java` pour le client1
`src\src_main\MainTest2Client.java` pour le client2.

# Lien git

https://github.com/stevenMarmion/sae_systeme_reseau
