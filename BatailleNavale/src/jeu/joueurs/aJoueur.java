package jeu.joueurs;

import java.util.HashMap;
import java.util.Map;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;
import jeu.inputOutput.iOutput;

public abstract class aJoueur implements iJoueur{

  private Plateau plateau;
  private Map<Coordonnee, Boolean> attaques;
  private String nom;
  private iOutput output;

  protected aJoueur (iOutput output) {
    this.output = output;
    this.plateau = new Plateau();
    this.attaques = new HashMap<>();
  }

  /**
   * getter pour l'attribut nom
   * 
   * @retun Nom du joueur
   */
  protected iOutput getOutput() {
    return output;
  }

  /**
   * setter pour l'attribut nom
   * 
   * @param String Nom du joueur
   */
  protected void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Getter pour l'attribut nom
   * 
   * @return Nom du joueur
   */
  @Override
  public String getNom() {
    return nom;
  }

  /**
   * Getter pour le plateau de jeu du joueur.
   * 
   * @return Le plateau de jeu du joueur.
   */
  @Override
  public Plateau getPlateau() {
    return plateau;
  }

  /**
   * Getter pour la liste des attaques effectuées par le joueur.
   * 
   * @return La liste des attaques effectuées par le joueur.
   */
  @Override
  public Map<Coordonnee, Boolean> getAttaques() {
    return attaques;
  }

  /**
   * Retourne le nom du joueur.
   *
   * @return Le nom du joueur.
   */
  @Override
  public String toString() {
    return this.nom;
  }

  /**
   * Méthode pour effectuer une attaque sur le plateau de l'adversaire.
   * 
   * @param plateauAdversaire Le plateau de l'adversaire.
   * @param coordonnee        La coordonnée à attaquer.
   */
  @Override
  public ResultatAttaque attaquer(Plateau plateauAdversaire, Coordonnee coord) {
    ResultatAttaque resultat = plateauAdversaire.recevoirAttaque(coord);
    if (!attaques.containsKey(coord)) {
      if (resultat != ResultatAttaque.MANQUE) {
        attaques.put(coord, true);
      } else {
        attaques.put(coord, false);
      }
    }
    return resultat;
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
}
