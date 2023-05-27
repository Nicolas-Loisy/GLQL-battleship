# GLQL-battleship

Projet GLQL IUT Descartes L3 MIAGE

Groupe : Nicolas Loisy / Clément Diot / Ita Maknine / Alexis Léon

Projet : Bataille navale

Titre : GLQL Battleship 

GLQL-battleship : [ Java/Réseau ]

## Installation
Le projet utilise Maven, il est donc nécéssaire de l'avoir préalablement installé.

1. Clonez le dépôt GitHub : `git clone https://github.com/Nicolas-Loisy/GLQL-battleship.git`
2. Accédez au répertoire du serveur : `cd BatailleNavale/serveur`
3. Compilez le code du serveur : `mvn compile`
4. Accédez au répertoire du client : `cd ../client`
5. Compilez le code du client : `mvn compile`

## Démarrage du serveur

1. Accédez au répertoire du serveur : `cd BatailleNavale/serveur`
2. Lancez le serveur : `mvn compile exec:java`

Le serveur sera démarré et écoutera les connexions des clients sur un port spécifique.

## Démarrage du client

1. Accédez au répertoire du client : `cd projet-bataille-navale/client`
2. Lancez le client : `mvn compile exec:java`

Le client se connectera au serveur et permettra au joueur de jouer contre le bot.
Le jeu comporte tous les éléments de jeu d'une Bataille Navale classique avec une règle en plus.
Règle bonus : Chaque bateau a une chance d'esquiver/de contrer une attaque.
La partie est configurée avec deux bateaux (Sous-marin et Croiseur)

## Structure du code

Le projet est organisé en deux parties :

- **Serveur** : contient les classes et les logiques du jeu côté serveur, y compris la gestion du plateau de jeu, des joueurs et des bateaux.
- **Client** : contient les classes et les logiques du jeu côté client, y compris l'interface utilisateur, les interactions avec le serveur et la gestion des actions du joueur.

Chaque partie du code est organisée dans des packages appropriés pour une meilleure lisibilité et maintenabilité.


## Tests JUnit

Le projet comprend des tests unitaires JUnit pour vérifier le bon fonctionnement des différentes parties du code. Les tests JUnit garantissent la qualité du code et aident à détecter les éventuels problèmes ou bugs.

Les tests JUnit sont organisés dans des classes de test correspondantes aux classes testées. Chaque méthode de test est annotée avec @Test et contient les assertions qui vérifient le comportement attendu du code.

## Exécution des tests JUnit

Pour exécuter les tests JUnit, vous pouvez suivre les étapes suivantes :

1. Assurez-vous que vous avez installé Maven sur votre machine.

2. Ouvrez une console ou un terminal et accédez au répertoire racine du projet.

3. Accédez au répertoire du serveur : `cd BatailleNavale/serveur`

4. Exécutez la commande suivante pour exécuter tous les tests du projet : `mvn test`

Cette commande exécutera tous les tests JUnit du projet et affichera les résultats dans la console ou le terminal.