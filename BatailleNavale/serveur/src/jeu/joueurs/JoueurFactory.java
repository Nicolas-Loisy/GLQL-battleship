package jeu.joueurs;

import java.io.IOException;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jeu.reseau.Serveur;

public class JoueurFactory {

  protected static final Logger logger = LogManager.getLogger(JoueurFactory.class);
  private static Serveur serveur;

  public static void setServeur(Serveur serveur) {
    JoueurFactory.serveur = serveur;
  }
  
  public static iJoueur creerJoueur(TypeJoueur JoueurType) throws IOException {
    logger.info("[SERVEUR] Création d'un joueur de type " + JoueurType);
    
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

