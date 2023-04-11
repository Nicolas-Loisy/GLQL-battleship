package jeu.inputOutput;

import java.util.Map;

import jeu.Coordonnee;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;

/**
 * Cette interface représente une sortie de jeu.
 */
public interface iOutput {
  // Gestion des affichages relatifs au différentes phases d'un tour
  public void msgDebutDePartie();
  public void msgFinDePartie(String nomGagnant);

  public void msgDebutPlacerBateaux(String nomJoueur);
  public void msgSaisieBateau(Bateau bateau, Plateau plateau);
  public void msgFinPlacerBateaux();

  public void msgDebutDeTour(String nomJoueur);
  public void msgFinDeTour();
  public void msgTourAdversaire();
  public void msgFinTourAdversaire();

  public void msgDebutAttaque(Map<Coordonnee, Boolean> attaques);
  public void msgFinAttaque(ResultatAttaque resultat, Coordonnee coord);
  public void msgFinAttaqueAdversaire(ResultatAttaque resultat, Coordonnee coord);

  // Autres affichages
  public void msgPlateau(Plateau plateau);
  public void msgError(String msg);
  public void msgSaisieCoordonnee();
  public void msgSaisieNom();
  public void msgSaisieNomAdversaire();
  public void msgSaisieOrientation();
}

