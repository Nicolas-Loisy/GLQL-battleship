package jeu.joueurs;

import java.io.IOException;
import java.util.List;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.inputOutput.TypeInputOutput;
import jeu.serveur.Serveur;

public class Hote extends aJoueur {
  private Serveur serveur;
  
  public Hote(TypeInputOutput inOutType, int port) throws IOException {
    super(inOutType);

    serveur = new Serveur(port);
    serveur.connexion();
  }

  @Override
  public String askUserNom() {
    String nom = null;

    return nom;
  }

  @Override
  public Coordonnee askUserCoordonnee() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'askUserCoordonnee'");
  }

  @Override
  public Orientation askUserOrientation() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'askUserOrientation'");
  }

  @Override
  public void placerBateaux(List<Bateau> bateauxAPlacer) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'placerBateaux'");
  }

  @Override
  public void msgDebutPartie() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'msgDebutPartie'");
  }

  @Override
  public void msgFinPartie(String nomGagnant) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'msgFinPartie'");
  }

  @Override
  public void msgDebutTour() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'msgDebutTour'");
  }

  @Override
  public void msgFinTour() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'msgFinTour'");
  }

  @Override
  public void msgDebutAttaque() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'msgDebutAttaque'");
  }

  @Override
  public void msgFinAttaque(ResultatAttaque resultat, Coordonnee coord) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'msgFinAttaque'");
  }
}
