package jeu.joueurs;

import java.io.IOException;

import jeu.inputOutput.TypeInputOutput;

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
}

