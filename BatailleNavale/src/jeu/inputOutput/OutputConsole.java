package jeu.inputOutput;

import java.util.Map;

import jeu.Coordonnee;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;

/**
 * La classe OutputConsole implémente l'interface iOutput et définit les
 * méthodes pour afficher
 * les messages à l'utilisateur via la console.
 */
public class OutputConsole implements iOutput {
  @Override
  public void msgDebutDePartie(){}

  @Override
  public void msgFinDePartie(String nomGagnant){
    System.out.println("\nFin de la partie\n" + nomGagnant.toUpperCase() + " A GAGNE !\n");
  }

  @Override
  public void msgDebutPlacerBateaux(String nomJoueur) {
    System.out.println("Au tour de " + nomJoueur + " de placer ses bateaux");
  }

  @Override
  public void msgSaisieBateau(Bateau bateau, Plateau plateau) {
    msgPlateau(plateau);
    System.out.println("Où placer le " + bateau.getNom() + " (Taille = " + bateau.getTaille() + ") : ");
  }

  @Override
  public void msgFinPlacerBateaux(){
    System.out.println("Tous les bateaux ont été placés");
  }

  /**
   * Affiche un message de début de tour pour un joueur donné.
   * 
   * @param joueur Le joueur qui va jouer.
   */
  @Override
  public void msgDebutDeTour(String nomJoueur) {
    System.out.println("\n" + nomJoueur + ", c'est ton tour !\n");
  }

  @Override
  public void msgFinDeTour(){
    System.out.println("Fin du tour");
  }

  @Override
  public void msgDebutAttaque(Map<Coordonnee, Boolean> attaques){
    System.out.println("Historique des attaques : ");
    afficherAttaques(attaques);
  }

  @Override
  public void msgFinAttaque(ResultatAttaque resultat, Coordonnee coord){
    System.out.println("Résultat de l'attaque en " + coord + " : " + resultat);
  }

  @Override
  public void msgFinAttaqueAdversaire(ResultatAttaque resultat, Coordonnee coord){
    System.out.println("Résultat de l'attaque de l'adversaire en " + coord + " : " + resultat);
  }

  @Override
  public void msgSaisieCoordonnee() {
    System.out.print("Veuillez saisir une coordonnée de type algébrique (ex: A1) : ");
  }

  @Override
  public void msgSaisieNom() {
    System.out.print("\nVeuillez saisir le nom du joueur : ");
  }

  @Override
  public void msgSaisieOrientation() {
    System.out.print("Veuillez saisir une orientation (H ou V) : ");
  }

  @Override
  public void msgTourAdversaire() {
    System.out.println("Veuillez attendre la fin du tour de l'adversaire...");
  }

  @Override
  public void msgFinTourAdversaire() {
    System.out.println("Fin du tour de l'adversaire !");
  }

  @Override
  public void msgSaisieNomAdversaire() {
    System.out.println("Veuillez attendre que votre adversaire entre le nom de son joueur...");
  }

  @Override
  public void msgError(String msg) {
    System.out.println(msg);
  }

  public void msgPlateau(Plateau plateau) {
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

  // FONCTIONS PRIVEES
  private void afficherAttaques(Map<Coordonnee, Boolean> attaques) {
    StringBuilder sb = new StringBuilder();
    int nbrLignes = Plateau.getNombreLigne();
    int nbrColonnes = Plateau.getNombreColonne();
    int ligne = 0;
    int colonne = 0;

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

  private String afficherBandeau(String message) {
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
}
