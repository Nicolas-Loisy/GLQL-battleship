package reseau;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
  private Socket socket;
  private PrintWriter out;
  private BufferedReader in;

  public Client(String ip, int port) {
    try {
      socket = new Socket(ip, port);
      out = new PrintWriter(socket.getOutputStream(), false);
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (Exception e) {
      System.err.println("Erreur lors de la connexion au serveur : " + e.getMessage());
      System.exit(1);
    }
  }

  public void envoyer(String message) {
    out.println(message);
    out.flush();
  }

  public String recevoir() {
    try {
      return in.readLine();
    } catch (Exception e) {
      System.err.println("Erreur lors de la réception de message : " + e.getMessage());
      
      return null;
    }
  }

  public void deconnexion() {
    try {
      in.close();
      out.close();
      socket.close();
      System.out.println("Deconnexion du serveur réussie");
    } catch (Exception e) {
      System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
    }
  }
}
