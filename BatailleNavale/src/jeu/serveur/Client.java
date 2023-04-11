package jeu.serveur;

import java.io.*;
import java.net.*;

public class Client extends aConnexion {
  private String adresse;
 
  public Client(String adresse, int port) {
    super(port);
    this.adresse = adresse;
  }

  public Socket connexion() throws IOException {
    Socket socket = null;
    
    // Ouverture de la socket
    try {
      socket = new Socket(adresse, super.getPort());
      System.out.println("Client : la socket a été crée avec succès.");
    } catch (IOException e) {
      System.err.println("Client : erreur lors de la création de la socket : " + e);
    }

    // Ouverture des flux
    try {
      super.setOut(new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))));
      super.setIn(new BufferedReader(new InputStreamReader(socket.getInputStream())));
    } catch (Exception e) {
      System.err.println("Client : Erreur lors de l'ouverture des flux : " + e);
    }

    return socket;
  }

  public boolean deconnexion() throws IOException {
    // Fermeture des flux
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
    try {
      super.getSocket().close();
    } catch (IOException e) {
      System.err.println("Client : erreur de fermeture de socket." + e);
      return false;
    }

    return true;
  }
}
