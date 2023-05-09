package jeu.joueurs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;

public abstract class aJoueur implements iJoueur{

  private Plateau plateau;
  private Map<Coordonnee, ResultatAttaque> attaques;
  private String nom;


  protected aJoueur () {
    this.plateau = new Plateau();
    this.attaques = new HashMap<>();
  }

  @Override
  public Plateau getPlateau() {
    return plateau;
  }

  protected Map<Coordonnee, ResultatAttaque> getAttaques(){
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
      attaques.put(coord, resultat);
    }
    
    return resultat;
  }

  @Override
  public ResultatAttaque recevoirAttaque(Coordonnee coord) {
    ResultatAttaque resultat = plateau.recevoirAttaque(coord);
    return resultat;
  }

  @Override
  public int getNbrBateauxRestants() {
    return plateau.getBateaux().size();
  }

  @Override
  public String getAttaquesHistorique() {
    Map<Coordonnee, ResultatAttaque> attaques = getAttaques();
    StringBuilder sb = new StringBuilder();
    
    for (Iterator<Coordonnee> it = attaques.keySet().iterator(); it.hasNext();) {
      Coordonnee coord = it.next();
      sb.append(coord).append(":").append(attaques.get(coord));
    
      if (it.hasNext()) {
        sb.append("&");
      }
    }
    
    return sb.toString();
  }

}
