package jeu.inputOutput;

import java.util.Map;

import jeu.Coordonnee;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.joueurs.iJoueur;

/**
 * La classe OutputConsole implémente l'interface iOutput et définit les
 * méthodes pour afficher
 * les messages à l'utilisateur via la console.
 */
public class OutputConsole implements iOutput {

  /**
   * Affiche un message de début de placement de bateau pour un joueur donné.
   * 
   * @param joueur Le joueur qui va placer ses bateaux.
   */
  @Override
  public void msgDebutPlacerBateau(String nomJoueur) {
    System.out.println("Au tour de " + nomJoueur + " de placer ses bateaux");
  }

  /**
   * Affiche le plateau de jeu pour un joueur donné.
   * 
   * @param plateau Le plateau de jeu à afficher.
   */
  @Override
  public void afficherPlateau(Plateau plateau) {
    StringBuilder sb = new StringBuilder();
    int nbrLignes = Plateau.getNombreLigne();
    int nbrColonnes = Plateau.getNombreColonne();
    int ligne = 0;
    int colonne = 0;

    sb.append(afficherBandeau("VOTRE PLATEAU") + "\n");

    // Affichage de l'entête de colonnes (chiffres 1 à 10)
    sb.append("  ");
    colonne++;
    for (; colonne <= nbrColonnes; colonne++) {
      sb.append(colonne).append(" ");
    }
    sb.append(System.lineSeparator());
    ligne++;

    // Affichage de chaque ligne
    for (; ligne <= nbrLignes; ligne++) {
      // Affichage de la lettre de la ligne
      colonne = 0;
      sb.append((char) ('A' + ligne - 1)).append(" ");
      colonne++;

      // Affichage des cellules de la ligne
      for (; colonne <= nbrColonnes; colonne++) {
        boolean celluleOccupee = false;
        if (!plateau.isCoordonneeLibre(new Coordonnee(ligne, colonne))) {
          celluleOccupee = true;
        }

        sb.append(celluleOccupee ? "X " : "~ ");
      }

      // Passage à la ligne suivante
      sb.append(System.lineSeparator());
    }
    System.out.println(sb.toString());
  }

  public void afficherAttaques(Map<Coordonnee, Boolean> attaques) {
    StringBuilder sb = new StringBuilder();
    int nbrLignes = Plateau.getNombreLigne();
    int nbrColonnes = Plateau.getNombreColonne();
    int ligne = 0;
    int colonne = 0;

    sb.append(afficherBandeau("VOS ATTAQUES") + "\n");
    // Affichage de l'entête de colonnes (chiffres 1 à 10)
    sb.append("  ");
    colonne++;
    for (; colonne <= nbrColonnes; colonne++) {
      sb.append(colonne).append(" ");
    }
    sb.append(System.lineSeparator());
    ligne++;
    // Affichage de chaque ligne
    for (; ligne <= nbrLignes; ligne++) {
      // Affichage de la lettre de la ligne
      colonne = 0;
      sb.append((char) ('A' + ligne - 1)).append(" ");
      colonne++;

      // Affichage des cellules de la ligne
      for (; colonne <= nbrColonnes; colonne++) {
        Coordonnee coordonnee = new Coordonnee(ligne, colonne);
        Boolean attaqueReussie = attaques.get(coordonnee);

        if (attaqueReussie != null && attaqueReussie) {
          sb.append("X ");
        } else if (attaqueReussie != null && !attaqueReussie) {
          sb.append("O ");
        } else {
          sb.append("~ ");
        }
      }

      // Passage à la ligne suivante
      sb.append(System.lineSeparator());
    }
    System.out.println(sb.toString());
  }

  /**
   * Affiche un bandeau encadré avec le message donné au centre.
   * 
   * @param message Le message à afficher dans le bandeau.
   * @return La chaîne de caractères représentant le bandeau encadré avec le
   *         message au centre.
   */
  public static String afficherBandeau(String message) {
    int longueurMessage = message.length();
    String margin = "  ";
    String ligneBandeau = margin + "+---" + "-".repeat(longueurMessage) + "--+";
    String ligneVide = margin + "|   " + " ".repeat(longueurMessage) + "  |";
    String ligneMessage = margin + "|   " + message + "  |";
    StringBuilder sb = new StringBuilder();
    sb.append(ligneBandeau).append("\n")
        .append(ligneVide).append("\n")
        .append(ligneMessage).append("\n")
        .append(ligneVide).append("\n")
        .append(ligneBandeau).append("\n");
    return sb.toString();
  }

  /**
   * Affiche un message demandant où placer un bateau.
   * 
   * @param bateau Le bateau à placer.
   */
  @Override
  public void msgPlacerBateau(Bateau bateau) {
    System.out.println("Où placer le " + bateau.getNom() + " ? (Taille = " + bateau.getTaille() + ")\n");
  }

  /**
   * Affiche un message de début de tour pour un joueur donné.
   * 
   * @param joueur Le joueur qui va jouer.
   */
  @Override
  public void msgDebutTour(iJoueur joueur) {
    System.out.println("\n" + joueur.getNom().toUpperCase() + ", C'EST TON TOUR !\n");
  }

  @Override
  public void changementTour() {
    System.out.println("\n=================================================================\n");
  }

  /**
   * Affiche le résultat d'une attaque sur une case.
   * 
   * @param resultat Le résultat de l'attaque.
   */
  @Override
  public void msgResultatAttaque(ResultatAttaque resultat) {
    switch (resultat) {
      case MANQUE:
        System.out.println("Vous avez manqué la cible !");
        break;
      case TOUCHE:
        System.out.println("Touché !");
        break;
      case COULE:
        System.out.println("Coulé !");
        break;
      case GAMEOVER:
        System.out.println("Bravo, vous avez gagné la partie !");
    }
  }

  /**
   * Affiche un message de victoire avec le nom du joueur gagnant.
   * 
   * @param gagnant le joueur gagnant.
   */
  @Override
  public void msgVictoire(iJoueur gagnant) {
    System.out.println("\nLe gagnant est : " + gagnant.getNom());
  }

  /**
   * Affiche un message demandant à l'utilisateur de saisir une coordonnée de type
   * algébrique.
   */
  @Override
  public void msgSaisieCoordonnee() {
    System.out.print("Veuillez saisir une coordonnée de type algébrique (ex: A1) : ");
  }

  /**
   * Affiche un message demandant à l'utilisateur de saisir le nom d'un joueur.
   */
  @Override
  public void msgSaisieNom() {
    System.out.print("\nVeuillez saisir le nom du joueur : ");
  }

  /**
   * Affiche un message demandant à l'utilisateur de saisir une orientation (H ou
   * V).
   */
  @Override
  public void msgSaisieOrientation() {
    System.out.print("Veuillez saisir une orientation (H ou V) : ");
  }

  /**
   * Affiche un message d'erreur.
   * 
   * @param msg le message d'erreur à afficher.
   */
  @Override
  public void msgError(String msg) {
    System.out.println(msg);
  }
}
