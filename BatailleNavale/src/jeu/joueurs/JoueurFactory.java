package jeu.joueurs;

import java.io.IOException;

import jeu.inputOutput.TypeInputOutput;
import jeu.serveur.iConnexion;

public class JoueurFactory {
  
  public static iJoueur creerJoueur(TypeJoueur JoueurType, TypeInputOutput inOutType) throws IOException {;
    switch(JoueurType) {
      case HUMAIN :
        return new Humain(inOutType);
      case HOTE :
        return new Hote(inOutType, 8080);
      case ORDI_HASARD :
        return new OrdiHasard(inOutType);
      default :
        return null;
    }
  }

  public static iJoueur creerJoueur(TypeJoueur JoueurType, TypeInputOutput inOutType, iConnexion connexion) throws IOException {;
    switch(JoueurType) {
      case HUMAIN_RESEAU :
        return new HumainReseau(inOutType, connexion);
      case HUMAIN_JOUEUR :
        return new HumainJoueur(inOutType, connexion);
      default :
        return null;
    }
  }
  
}

