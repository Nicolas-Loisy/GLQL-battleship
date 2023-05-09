package jeu.joueurs;

import java.io.IOException;
import java.net.Socket;

import jeu.reseau.Serveur;

public class JoueurFactory {
  private static Serveur serveur;

  public static void setServeur(Serveur serveur) {
    JoueurFactory.serveur = serveur;
  }
  
  public static iJoueur creerJoueur(TypeJoueur JoueurType) throws IOException {
    System.out.println("[SERVEUR] Création d'un joueur de type " + JoueurType);
    
    switch(JoueurType) {
    
      case ORDI_HASARD :
        return new OrdiHasard();
      case HUMAIN :
        Socket socket = serveur.attendreConnexion();
        return new Humain(socket);
      default :
        return null;
    }
  }
}

