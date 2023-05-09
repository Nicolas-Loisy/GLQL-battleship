package jeu.reseau;

import java.io.*;
import java.net.*;
import java.util.logging.Logger;

public class Serveur {
  private ServerSocket serveur;
  private Socket[] sockets;
  private int nbrJoueursConnectes;
  private Logger logger;

  public Serveur(int port, int nbrJoueurs) {
    sockets = new Socket[nbrJoueurs];
    nbrJoueursConnectes = 0;
    logger = Logger.getAnonymousLogger();

    try {
      serveur = new ServerSocket(port);
      logger.info("Ouverture du serveur...");
    } catch (IOException e) {
      logger.info("Ouverture du serveur...");
      e.printStackTrace();
    }
  }

  public Socket attendreConnexion() {
    if (nbrJoueursConnectes >= sockets.length) {
      System.err.println("[SERVEUR] nombre maximum de joueurs atteint.");
      
      return null;
    }

    try {
      logger.info("Attente de la connexion d'un joueur...");
      
      Socket socket = serveur.accept();
      logger.info("Connexion du client " + socket.getRemoteSocketAddress().toString());
      nbrJoueursConnectes++;
      
      return socket;
    } catch (IOException e) {
      System.err.println("[SERVEUR] erreur lors de l'ouverture de l'accès : " + e);
      return null;
    }
  }

  public void deconnexion() {
    for (int i = 0; i < sockets.length; i++) {
      
      // Fermeture de la socket
      if (sockets[i] != null) {
        try {
          logger.info("Deconnexion du client " + sockets[i].toString());
          sockets[i].close();
        } catch (IOException e) {
          System.err.println("[SERVEUR] erreur de fermeture de socket." + e);
        }
      }
    }

    // Fermeture de la socket serveur
    if (serveur != null) {
      try {
        serveur.close();
        logger.info("Fermeture du serveur réussi");
      } catch (IOException e) {
        logger.info("Fermeture du serveur réussi");
        System.err.println("[SERVEUR] erreur de fermeture de socket serveur." + e);
      }
    }
  }
}
