package jeu.joueurs;

import java.io.IOException;
import java.util.List;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.exceptions.ReglesException;
import jeu.exceptions.TypeException;
import jeu.inputOutput.TypeInputOutput;
import jeu.serveur.iConnexion;


/**
 * Classe représentant un joueur de la bataille navale.
 */
public class HumainJoueur extends aJoueur {

  private iConnexion connexion;

  public HumainJoueur(TypeInputOutput inOutType, iConnexion connexion) {
    super(inOutType);
    this.connexion = connexion;
    super.setNom(askUserNom());
  }

  @Override
  public String askUserNom() {
    String nom = null;
    while (nom == null) {
      try {
        super.getOutput().msgSaisieNom();
        nom = super.getIn().getNomJoueur();
      } catch (ReglesException e) {
        super.getOutput().msgError(e.getMessage());
      }
    }

    try {
      this.connexion.envoyer(nom);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return nom;
  }

  @Override
  public Coordonnee askUserCoordonnee() {
    Coordonnee coord = null;
    while (coord == null) {
      try {
        super.getOutput().msgSaisieCoordonnee();
        String strCoord = super.getIn().getCoordonnee();
        coord = new Coordonnee(strCoord);
        if (!super.getPlateau().isCoordonneeSurPlateau(coord)){
          coord = null;
          throw new ReglesException(TypeException.HORS_PLATEAU_ERROR);
        }
      } catch (ReglesException e) {
        super.getOutput().msgError(e.getMessage());
      }
    }

    try {
      this.connexion.envoyer(coord.toString());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return coord;
  }

  @Override
  public Orientation askUserOrientation() {
    Orientation orientation = null;
    while (orientation == null) {
      try {
        super.getOutput().msgSaisieOrientation();
        orientation = super.getIn().getOrientation();
      } catch (ReglesException e) {
        super.getOutput().msgError(e.getMessage());
      }
    }

    try {
      this.connexion.envoyer(orientation.name());
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return orientation;
  }

  @Override
  public void placerBateaux(List<Bateau> bateauxAPlacer) {
    super.getOutput().msgDebutPlacerBateaux(super.getNom());

    for (Bateau bateau : bateauxAPlacer) {
      Boolean bateauPlace = false;
      while (!bateauPlace) {
        super.getOutput().msgSaisieBateau(bateau, super.getPlateau());
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

    super.getOutput().msgFinPlacerBateaux();
  }

  @Override
  public void msgDebutPartie(){
    super.getOutput().msgDebutDePartie();
  }

  @Override
  public void msgFinPartie(String nomGagnant){
    super.getOutput().msgFinDePartie(nomGagnant);
  }

  @Override
  public void msgDebutTour(){
    super.getOutput().msgDebutDeTour(super.getNom());
    super.getOutput().msgPlateau(super.getPlateau());
  }

  @Override
  public void msgFinTour(){
    super.getOutput().msgFinDeTour();
  }

  @Override
  public void msgDebutAttaque(){
    super.getOutput().msgDebutAttaque(super.getAttaques());
  }

  @Override
  public void msgFinAttaque(ResultatAttaque resultat, Coordonnee coord){
    super.getOutput().msgFinAttaque(resultat, coord);
  }

  protected void msgError(ReglesException e){
    super.getOutput().msgError(super.getNom());
  }

}
