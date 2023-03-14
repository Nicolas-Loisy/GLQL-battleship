package jeu.joueurs;

import jeu.inputOutput.iInput;
import jeu.inputOutput.iOutput;

public class JoueurFactory {
  
  /**
   * Retourne un joueur du type spécifié avec un nom donné.
   *
   * @param type Le type de joueur souhaité.
   * @param nom Le nom du joueur.
   * @return Un joueur du type spécifié avec un nom donné.
   */
  public static iJoueur getJoueur(TypeJoueur type, iInput input, iOutput output) {
    switch(type) {
      case HUMAIN :
        return new Humain(input, output);
      case ORDI_HASARD :
        return null; // TODO : implémenter l'ordinateur
      default :
        return null;
    }
  }
}

