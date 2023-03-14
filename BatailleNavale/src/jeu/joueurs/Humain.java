package jeu.joueurs;

import java.util.List;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;
import jeu.inputOutput.iInput;
import jeu.inputOutput.iOutput;

/**
 * Classe représentant un joueur de la bataille navale.
 */
public class Humain extends aJoueur {

  iInput input;
  iOutput output;

  /**
   * Constructeur de la classe Joueur.
   * 
   * @param plateau Le plateau de jeu du joueur.
   */
  public Humain(iInput input, iOutput output) {
    super();
    this.input = input;
    this.output = output;
    String nom = askUserNom();
    super.setNom(nom);
  }

  /**
   * Demande à l'utilisateur de saisir un nom de joueur.
   * 
   * @return le nom saisi par l'utilisateur
   */
  @Override
  public String askUserNom() {
    String nom = null;
    while (nom == null) {
      try {
        output.msgSaisieNom();
        nom = input.getNomJoueur();
      } catch (ReglesException e) {
        output.msgError(e.getMessage());
      }
    }
    return nom;
  }

  /**
   * Demande à l'utilisateur de saisir une coordonnée.
   * 
   * @return la coordonnée saisie par l'utilisateur
   */
  @Override
  public Coordonnee askUserCoordonnee() {
    Coordonnee coord = null;
    while (coord == null) {
      try {
        output.msgSaisieCoordonnee();
        String strCoord = input.getCoordonnee();
        coord = new Coordonnee(strCoord);
      } catch (ReglesException e) {
        output.msgError(e.getMessage());
      }
    }
    return coord;
  }

  /**
   * Demande à l'utilisateur de saisir une orientation.
   * 
   * @return l'orientation saisie par l'utilisateur
   */
  @Override
  public Orientation askUserOrientation() {
    Orientation orientation = null;
    while (orientation == null) {
      try {
        output.msgSaisieOrientation();
        orientation = input.getOrientation();
      } catch (ReglesException e) {
        output.msgError(e.getMessage());
      }
    }
    return orientation;
  }

  /**
   * Place les bateaux d'un joueur sur son plateau
   *
   * @param joueur         le joueur qui place ses bateaux
   * @param bateauxAPlacer la liste des bateaux à placer
   */
  @Override
  public void placerBateaux(List<Bateau> bateauxAPlacer) {
    output.msgDebutPlacerBateau(super.getNom());
    for (Bateau bateau : bateauxAPlacer) {
      output.afficherPlateau(super.getPlateau());
      Boolean bateauPlace = false;
      while (!bateauPlace) {
        output.msgPlacerBateau(bateau);
        Coordonnee coordDepart = askUserCoordonnee();
        Orientation orientation = askUserOrientation();
        try {
          super.placerBateau(bateau, coordDepart, orientation);
          bateauPlace = true;
        } catch (ReglesException e) {
          output.msgError(e.getMessage());
        }
      }
    }
  }
}
