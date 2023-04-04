package jeu.joueurs;

import java.util.List;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;

public interface iJoueur {

  public String getNom();
  public String askUserNom();
  public Coordonnee askUserCoordonnee();
  public Orientation askUserOrientation();
  public void placerBateaux(List<Bateau> bateauxAPlacer);
  public ResultatAttaque attaquer(iJoueur joueurAdverse, Coordonnee coordonnee);
  public ResultatAttaque recevoirAttaque(Coordonnee coord);

  //Fonction d'affichage
  public void msgDebutPartie();
  public void msgFinPartie(String nomGagnant);
  public void msgDebutTour();
  public void msgFinTour();
  public void msgDebutAttaque();
  public void msgFinAttaque(ResultatAttaque resultat, Coordonnee coord);
}
