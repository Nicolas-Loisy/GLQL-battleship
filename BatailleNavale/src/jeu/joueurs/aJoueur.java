package jeu.joueurs;

import java.util.HashMap;
import java.util.Map;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;
import jeu.inputOutput.InputOutputFactory;
import jeu.inputOutput.TypeInputOutput;
import jeu.inputOutput.iInput;
import jeu.inputOutput.iOutput;

public abstract class aJoueur implements iJoueur{

  private Plateau plateau;
  private Map<Coordonnee, Boolean> attaques;
  private String nom;

  private iOutput out;
  private iInput in;

  protected aJoueur (TypeInputOutput inOutType) {
    out = InputOutputFactory.getOutput(inOutType);
    in = InputOutputFactory.getInput(inOutType);

    this.plateau = new Plateau();
    this.attaques = new HashMap<>();
  }

  protected iOutput getOutput() {
    return out;
  }

  protected iInput getIn() {
    return in;
  }

  protected Plateau getPlateau() {
    return plateau;
  }

  protected Map<Coordonnee, Boolean> getAttaques(){
    return attaques;
  }

  @Override
  public String getNom() {
    return nom;
  }

  protected String setNom(String nom) {
    return this.nom = nom;
  }

  @Override
  public String toString() {
    return this.nom;
  }

  /**
   * Place le bateau sur le plateau de jeu à partir d'une coordonnée de départ et
   * d'une orientation donnée.
   * Les coordonnées du bateau sont calculées en fonction de l'orientation.
   * Si le placement n'est pas conforme aux règles du jeu, une exception
   * ReglesException est levée.
   *
   * @param bateau      Le bateau à placer sur le plateau de jeu.
   * @param coordDepart La coordonnée de départ à partir de laquelle le bateau
   *                    doit être placé.
   * @param orientation L'orientation du bateau (verticale ou horizontale).
   * @throws ReglesException Si le placement n'est pas conforme aux règles du jeu.
   */
  protected void placerBateau(Bateau bateau, Coordonnee coordDepart, Orientation orientation) throws ReglesException {
    int taille = bateau.getTaille();
    Coordonnee[] coordonnees = new Coordonnee[taille];
    // Calcul des coordonnées en fonction de l'orientation
    if (orientation == Orientation.HORIZONTAL) {
      for (int i = 0; i < taille; i++) {
        coordonnees[i] = new Coordonnee(coordDepart.getLigne(), coordDepart.getColonne() + i);
      }
    } else if (orientation == Orientation.VERTICAL) {
      for (int i = 0; i < taille; i++) {
        coordonnees[i] = new Coordonnee(coordDepart.getLigne() + i, coordDepart.getColonne());
      }
    }

    try {
      plateau.placerBateau(bateau, coordonnees);
    } catch (ReglesException e) {
      throw e;
    }
  }

  @Override
  public ResultatAttaque attaquer(iJoueur joueurAdverse, Coordonnee coord) {
    ResultatAttaque resultat = joueurAdverse.recevoirAttaque(coord);
    if (!attaques.containsKey(coord)) {
      if (resultat != ResultatAttaque.MANQUE) {
        attaques.put(coord, true);
      } else {
        attaques.put(coord, false);
      }
    }
    return resultat;
  }

  @Override
  public ResultatAttaque recevoirAttaque(Coordonnee coord) {
    ResultatAttaque resultat = plateau.recevoirAttaque(coord);
    return resultat;
  }
}
