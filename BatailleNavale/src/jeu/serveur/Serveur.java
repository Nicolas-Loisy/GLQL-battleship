package jeu.serveur;

import java.io.*;
import java.net.*;

public class Serveur {
  private int port;
  private ServerSocket serveur;
  private Socket socket;
  private BufferedReader in; // flux de réception de données
  private PrintWriter out; // flux d'envoi de données

  public Serveur(int port) {
    this.port = port;
  }

  public boolean connexion() {
    try {
      serveur = new ServerSocket(port);
      System.out.println("Le serveur est en attente de connexions...");
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

    if (serveur != null) {
      try {
        this.socket = serveur.accept();
        System.out.println(
          "Serveur : Connexion du client " + socket.getRemoteSocketAddress().toString() + " est acceptée !");
      } catch (IOException e) {
        System.err.println("Serveur : erreur lors de l'ouverture de l'acces : " + e);
      }
    }

    if (socket != null) {
      try {
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      } catch (Exception e) {
        System.err.println("Client : Erreur lors de l'ouverture des flux : " + e);
        return false;
      }
    }

    return true;
  }

  public boolean deconnexion() {
    if (in != null && out != null) {
      try {
        in.close();
        out.close();
      } catch (Exception e) {
        System.err.println("Client : Erreur lors de la fermeture des flux : " + e);
        return false;
      }
    }

    // Fermeture de la socket
    if (socket != null) {
      try {
        socket.close();
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
