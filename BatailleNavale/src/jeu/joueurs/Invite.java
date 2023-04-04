package jeu.joueurs;

import java.io.IOException;
import java.util.List;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;
import jeu.inputOutput.TypeInputOutput;
import jeu.serveur.Client;


/**
 * Classe représentant un joueur de la bataille navale.
 */
public class Invite extends aJoueur{
  private Client client;

  public Invite(TypeInputOutput inOutType, String adresseIp, int port) throws IOException {
    super(inOutType);
    client = new Client(adresseIp, port);
    client.connexion();
  }
  
  public Client getClient() {
    return client;
  }

  @Override
  public String askUserNom() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'askUserNom'");
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
