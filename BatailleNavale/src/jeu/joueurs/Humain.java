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

  /**
   * Constructeur de la classe Joueur.
   * 
   * @param plateau Le plateau de jeu du joueur.
   */
  public Humain(iInput input, iOutput output) {
    super(output);
    this.input = input;
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
        super.getOutput().msgSaisieNom();
        nom = input.getNomJoueur();
      } catch (ReglesException e) {
        super.getOutput().msgError(e.getMessage());
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
        super.getOutput().msgSaisieCoordonnee();
        String strCoord = input.getCoordonnee();
        coord = new Coordonnee(strCoord);
      } catch (ReglesException e) {
        super.getOutput().msgError(e.getMessage());
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
        super.getOutput().msgSaisieOrientation();
        orientation = input.getOrientation();
      } catch (ReglesException e) {
        super.getOutput().msgError(e.getMessage());
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
    super.getOutput().msgDebutPlacerBateau(super.getNom());
    for (Bateau bateau : bateauxAPlacer) {
      super.getOutput().afficherPlateau(super.getPlateau());
      Boolean bateauPlace = false;
      while (!bateauPlace) {
        super.getOutput().msgPlacerBateau(bateau);
        Coordonnee coordDepart = askUserCoordonnee();
        Orientation orientation = askUserOrientation();
        try {
          super.placerBateau(bateau, coordDepart, orientation);
          bateauPlace = true;
        } catch (ReglesException e) {
          super.getOutput().msgError(e.getMessage());
        }
      }
    }
  }
}
