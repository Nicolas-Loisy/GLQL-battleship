package jeu.reseau;

import java.io.*;
import java.net.*;

public class Serveur {
  private ServerSocket serveur;
  private Socket[] sockets;
  private int nbrJoueursConnectes;

  public Serveur(int port, int nbrJoueurs) {
    sockets = new Socket[nbrJoueurs];
    nbrJoueursConnectes = 0;

    try {
      serveur = new ServerSocket(port);
      System.out.println("[SERVEUR] Ouverture du serveur...");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Socket attendreConnexion() {
    if (nbrJoueursConnectes >= sockets.length) {
      System.err.println("[SERVEUR] nombre maximum de joueurs atteint.");
      return null;
    }

    try {
      System.out.println("[SERVEUR] Attente de la connexion d'un joueur...");
      Socket socket = serveur.accept();
      System.out.println(
        "[SERVEUR] Connexion du client " + socket.getRemoteSocketAddress().toString() + " est acceptée !");
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
      } catch (IOException e) {
        System.err.println("[SERVEUR] erreur de fermeture de socket serveur." + e);
      }
    }
  }
}
