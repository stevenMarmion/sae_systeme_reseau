#!/bin/bash

# Compile les fichiers .java du dossier src sous javafx
javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin/ src/src_interface/*.java

# Condition si la première commande s'est effectué
if [ $? -eq 0 ]; then
  # Compile l'Executable du dossier bin
  java --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -cp bin bin.src_interface.ConnexionInscription
else
  # Si la compilation échoue, nous renvoyons le message suivant :
  echo "La compilation a échoué, regardez les messages d'erreurs ce-dessus"
  # On implique que les messages d'erreurs de compilation java ce sont affcihés au dessus
fi
