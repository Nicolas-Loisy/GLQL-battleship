package jeu.serveur;

import java.io.*;
import java.net.*;

public class Client {
  private String adresse;
  private int port;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;

  public Client(String adresse, int port) {
    this.adresse = adresse;
    this.port = port;
  }

  public boolean connexion() throws IOException {
    // Ouverture de la socket
    try {
      socket = new Socket(adresse, port);
      System.out.println("Client : la socket a été crée avec succès.");
    } catch (IOException e) {
      System.err.println("Client : erreur lors de la création de la socket : " + e);
      return false;
    }

    // Ouverture des flux
    try {
      out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (Exception e) {
      System.err.println("Client : Erreur lors de l'ouverture des flux : " + e);
      return false;
    }

    return true;
  }

  public boolean deconnexion() throws IOException {
    // Fermeture des flux
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
    try {
      socket.close();
    } catch (IOException e) {
      System.err.println("Client : erreur de fermeture de socket." + e);
      return false;
    }

    return true;
  }

  public void envoyer(String message) throws IOException {
    out.println(message);
  }

  public String recevoir() throws IOException {
    return in.readLine();
  }
}
