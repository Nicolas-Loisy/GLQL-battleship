package jeu.joueurs;

import java.io.IOException;
import java.util.List;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;

public interface iJoueur {

  public String getNom();
  public Plateau getPlateau();
  public String saisieNom() throws IOException;
  public Coordonnee saisieCoordonnee() throws IOException;
  public Orientation saisieOrientation() throws IOException;
  public void placerBateaux(List<Bateau> bateauxAPlacer) throws IOException;
  public ResultatAttaque attaquer(iJoueur joueurAdverse, Coordonnee coordonnee);
  public ResultatAttaque recevoirAttaque(Coordonnee coord);
  public int getNbrBateauxRestants();
  public String getAttaquesHistorique();

  //Fonction d'affichage
  public String communiquer(TypeCommunication typeCom, TypeClefs clef, String... params) throws IOException;
}
