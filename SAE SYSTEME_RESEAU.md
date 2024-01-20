# **SAE SYSTEME/RESEAU**

## Membres

Marmion Steven
SIMON Gael
22A

## Contexte

Développer une application client-serveur d’un réseau social : les utilisateurs peuvent publier des messages, et suivre d’autres utilisateurs pour consulter leurs publications.

## Projet 

L'application devra fonctions sous deux aspects :

- le client qui doit se connecter au serveur pour afficher les messages des personnes qu’il suit et poster ses propres publications,
- le serveur qui reçoit les messages, et les réexpédie aux bons utilisateurs.


## Manuel Utilisateur - ReseauIuto

### Introduction
- 1.1 Configuration

### Démarrage Rapide
- 2.1 Création de Compte
- 2.2 Connexion

### Commandes Utilisateurs
- 3.1 Consultation
     - 3.1.1 Messages Abonnés
     - 3.1.2 Mon Profil
- 3.2 Abonnement
    - 3.2.1 Suivre
    - 3.2.2 Se Désabonner
- 3.3 Messages
    - 3.3.1 Aimer
  	- 3.3.2 Ne plus Aimer
  	- 3.3.3 Retirer

### Commandes Serveur
- 4.1 Suppression
    - 4.1.1 Utilisateur
    - 4.1.2 Message Utilisateur

### Introduction

#### Configuration

Pour la configuration, un driver mariadb vous est transmit, vous avez simplement à l'ajouter dans la partie JAVA PROJECT ( sous vscode, ouvre n'importe quel fichier .java, l'IDE créera alors un petit onglet JAVA PROJECTS tout en bas à gauche de l'IDE. Cliquez sur JAVA PROJECTS et scroller jusque apercevoir "Referenced librairies", cliquez sur le plus sur la même ligne de cette onglet. Ajouter le driver mis à votre disposition et votre configuration est terminée côté librairie )

Pour le côté Mysql. Veuillez ouvrir votre serveur Mysql depuis la racine du projet, créer une base de données nommée "reseau_social" et exécuter le fichier all.sql avec la commande 

```
source DB/all.sql;
```

Vous aurez alors toutes les tables et un isnert de client administrateur.

Pour le côté driver, veuillez aller dans le fichier src/src_class/Server.java, cliquez dessus et aller dans la fonction connecteServerBDD(). Changer les deux denriers paramètres par votre nom qui vous permet de démarrer le serveur mysql et votre mot de passe.  

Si jusque là vous n'avez pas eu d'erreurs, alors vous avez finis de configurer l'application.

### Démarrage Rapide

2.1 Création de Compte

Pour lancer l'application, veuillez cliquer sur le fichier src/src_main/MainTestServeur.java et exécuter le le programme. Aller dans src/src_main et lancer les deux fichiers MainTestClient1 et MainTestClient2 avec le bouton play en haut à droite de l'IDE. Les deux clients auront  automatiquement leur compte dans la BDD même si on ne les mets pas dans les insert.

Pour tout ce qui est commande suivante : vous avez juste à faire du copier / coller pour les tester

### Commandes Utilisateurs

3.1 **Consultation**
3.1.1 Messages Abonnés

```
consulter
```

3.1.2 Mon Profil

```
profil
```


3.2 **Abonnement**
3.2.1 Suivre

```
/follow <name_client>
```

3.2.2 Se Désabonner

```
/unfollow <name_client>
```

3.3 **Messages**
3.3.1 Aimer

*Après avoir exécuter ```consulter```, vous pouvez voir les id_messages disponible.*

```
/like <idMessage>
```

3.3.2 Supprimer un de mes messages 
```
/delete <id_message>
```

### Commandes Serveur

4.1 Suppression
4.1.1 Utilisateur

*Pour tout id_client, merci de regarder dans la BDD*
```
/remove <id_client>
```

4.1.2 Message Utilisateur
```
/delete <id_message>
```


## Choix technique 

Tout d'abord un des premiers choix que nous avons dû faire a été de concevoir notre projet en JAVA côté client et serveur, si nous en avons décidé ainsi c'est pour une raison très simple, puisque nous avions travailler le développement client-serveur en python. Nous nous devions d'apprendre le même principe en JAVA.

Ensuite pour l'affichage des informations nous passons par la console.
Pour le client se sera tous les message et information relative mais pour le serveur c'est différent.
En effet, nous avons décidés de mettre en place un système de débuggage de l'application. La console du serveur affichera toutes les logs de ces agissements. Chaque fonctions, commandes démarrées par une action de l'utilisateur fera afficher le chemin parcourut par le serveur dans la console pour afficher l'algorithme présent dérrière. L'avantage principal de ce système de débuggage permet une maintenabilité plus facile côté développeur. Chaque développeur qui pourrait travailler sur une issue de l'application pourrait plus facilement débugger son travail. 
L'avantage se trouve aussi dans la lisibilité grâce aux logs affichées.

Pour stocker les différente information nous avons choisie d'utliser une BD mysql; Voici notre MCD : 
![MCD](https://hackmd.io/_uploads/SJu4UXFt6.png)


Donc des messages peuvent être écrit par un et un seul client et un client peut ecrire 0 ou N messages.
De plus un Client à 0 ou N abonnements et inversement un Client peut avoir 0 ou N abonnés.
L'attribut isAdmin de la table Client permettra à un Client d'être considéré en tant qu'administrateur ce qui lui conferera des droits spéciaux comme supprimer un Client ou un message de n'importe quel client.

Enfin pour sécuriser les échanges et éviter les conflits entre thread nous avons décider d'utiliser un lock, instancier sur le serveur et qui permettra d'éviter au thread de se bloquer mutuellement. Ce lock permet de garder et de gérer l'accès exclusif sur les datas de la BDD. Le but derrière tout ça est de pouvoir avoir ce côté producteur / consommateur


## Ressentis

Cette partie sera courte et exprimera notre ressentie par rapport à cette SAE.
Tout d'abord, cette SAE était très interresante à réaliser car elle est plutôt originale et change par rapport aux autre SAE. En effet, de par le choix du langage ou du visuel, elle nous permettait d'être plus libre dans nos choix de conception.
Cependant, d'après nous il serait vraiment très interressant de réaliser ce genre de SAE sur une semaine complète comme pour le projets VAE de l'année dernière. Cela nous permettrait de nous concentrer sur un seul projet pendant une periode, certe courte, mais de s'investir beaucoup plus dessus.

