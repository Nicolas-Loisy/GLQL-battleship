package jeu.inputOutput;

import java.util.Map;

import jeu.Coordonnee;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.joueurs.iJoueur;

/**
 * Cette interface représente une sortie de jeu.
 */
public interface iOutput {
  /**
   * Affiche le plateau spécifié.
   * @param plateau Le plateau à afficher.
   */
  public void afficherPlateau(Plateau plateau);

  /**
   * Affiche le plateau spécifié.
   * @param plateau Le plateau à afficher.
   */
  public void afficherAttaques(Map<Coordonnee, Boolean> attaques);
  /**
   * Affiche un message demandant à l'utilisateur de saisir une coordonnée.
   */
  public void msgSaisieCoordonnee();

  /**
   * Affiche un message demandant à l'utilisateur de saisir un nom.
   */
  public void msgSaisieNom();

  /**
   * Affiche un message demandant à l'utilisateur de saisir une orientation.
   */
  public void msgSaisieOrientation();

  /**
   * Affiche un message indiquant le début de la phase de placement des bateaux pour le joueur spécifié.
   * @param joueur Le joueur dont c'est le tour de placer les bateaux.
   */
  public void msgDebutPlacerBateau(String nomJoueur);

  /**
   * Affiche un message indiquant que le bateau spécifié doit être placé.
   * @param bateau Le bateau à placer.
   */
  public void msgPlacerBateau(Bateau bateau);

  /**
   * Affiche un message indiquant un changement de joueur
   */
  public void changementTour();

  /**
   * Affiche un message indiquant le début du tour pour le joueur spécifié.
   * @param joueur Le joueur dont c'est le tour.
   */
  public void msgDebutTour(iJoueur joueur);

  /**
   * Affiche le résultat de l'attaque spécifiée.
   * @param resultat Le résultat de l'attaque.
   */
  public void msgResultatAttaque(ResultatAttaque resultat);

  /**
   * Affiche un message indiquant que le joueur spécifié a gagné.
   * @param joueur Le joueur qui a gagné.
   */
  public void msgVictoire(iJoueur joueur);

  /**
   * Affiche un message d'erreur avec le message spécifié.
   * @param msg Le message d'erreur à afficher.
   */
  public void msgError(String msg);
}

