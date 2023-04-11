package jeu.joueurs;

import java.io.IOException;
import java.util.List;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;
import jeu.inputOutput.TypeInputOutput;
import jeu.serveur.iConnexion;


/**
 * Classe représentant un joueur de la bataille navale.
 */
public class HumainReseau extends aJoueur {

  private iConnexion connexion;

  public HumainReseau(TypeInputOutput inOutType, iConnexion connexion) {
    super(inOutType);
    this.connexion = connexion;
    super.setNom(askUserNom());
  }

  @Override
  public String askUserNom() {
    String nom = null;
    try {
      super.getOutput().msgSaisieNomAdversaire();
      nom = this.connexion.recevoir();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return nom;
  }

  @Override
  public Coordonnee askUserCoordonnee() {
    Coordonnee coord = null;
    try {
      String strCoord;
      strCoord = this.connexion.recevoir();
      coord = new Coordonnee(strCoord);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return coord;
  }

  @Override
  public Orientation askUserOrientation() {
    String orientationStr = null;
    try {
      orientationStr = this.connexion.recevoir();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return Orientation.valueOf(orientationStr);
  }

  @Override
  public void placerBateaux(List<Bateau> bateauxAPlacer) {
    for (Bateau bateau : bateauxAPlacer) {
      Boolean bateauPlace = false;
      while (!bateauPlace) {
        Coordonnee coordDepart = askUserCoordonnee();
        Orientation orientation = askUserOrientation();
        try {
          super.placerBateau(bateau, coordDepart, orientation);
          bateauPlace = true;
        } catch (ReglesException e) {}
      }
    } 
  }

  @Override
  public void msgDebutPartie(){
  }

  @Override
  public void msgFinPartie(String nomGagnant){
  }

  @Override
  public void msgDebutTour(){
    super.getOutput().msgTourAdversaire();
  }

  @Override
  public void msgFinTour(){
    super.getOutput().msgFinTourAdversaire();
  }

  @Override
  public void msgDebutAttaque(){
  }

  @Override
  public void msgFinAttaque(ResultatAttaque resultat, Coordonnee coord){
    super.getOutput().msgFinAttaqueAdversaire(resultat, coord);
  }

  protected void msgError(ReglesException e){
  }

}
