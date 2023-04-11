package jeu.serveur;

import java.io.*;
import java.net.*;

public class Serveur extends aConnexion {
  private ServerSocket serveur;

  public Serveur(int port) {
    super(port);
  }

  public Socket connexion() {
    try {
      serveur = new ServerSocket(super.getPort());
      System.out.println("Le serveur est en attente de connexions...");
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (serveur != null) {
      try {
        super.setSocket(serveur.accept());
        System.out.println(
          "Serveur : Connexion du client " + super.getSocket().getRemoteSocketAddress().toString() + " est acceptée !");
      } catch (IOException e) {
        System.err.println("Serveur : erreur lors de l'ouverture de l'acces : " + e);
      }
    }

    if (super.getSocket() != null) {
      try {
        super.setOut(new PrintWriter(new BufferedWriter(new OutputStreamWriter(super.getSocket().getOutputStream()))));
        super.setIn(new BufferedReader(new InputStreamReader(super.getSocket().getInputStream())));
      } catch (Exception e) {
        System.err.println("Client : Erreur lors de l'ouverture des flux : " + e);
      }
    }

    return super.getSocket();
  }

  public boolean deconnexion() {
    if (super.getIn() != null && super.getOut() != null) {
      try {
        super.getIn().close();
        super.getOut().close();
      } catch (Exception e) {
        System.err.println("Client : Erreur lors de la fermeture des flux : " + e);
        return false;
      }
    }

    // Fermeture de la socket
    if (super.getSocket() != null) {
      try {
        super.getSocket().close();
      } catch (IOException e) {
        System.err.println("Serveur : erreur de fermeture de socket." + e);
        return false;
      }
    }

    // Fermeture de la socket serveur
    if (serveur != null) {
      try {
        serveur.close();
      } catch (IOException e) {
        System.err.println("Serveur : erreur de fermeture de socket serveur." + e);
        return false;
      }
    }

    return true;
  }
}
