import inputOutput.InputOutputFactory;
import inputOutput.TypeInOut;
import inputOutput.iInput;
import inputOutput.iOutput;
import reseau.Client;

public class MainClient {
  public static void main(String[] args) {
    System.out.println("[DEBUT CLIENT]\n");
    
    // Définir les paramètres de connexion serveur
    final String ip = "localhost";
    final int port = 8080;

    // Se connecter au serveur
    Client client = new Client(ip, port);

    // Création d'un joueur
    TypeInOut typeInOut = TypeInOut.CONSOLE;
    iInput in = InputOutputFactory.getInput(typeInOut);
    iOutput out = InputOutputFactory.getOutput(typeInOut);

    String clef = "";

    while (!clef.equals("PARTIE_FIN")) {
      String msgServeur = client.recevoir();
      String[] splitMsg = msgServeur.split("#");
      String typeMessage = splitMsg[0];
      clef = splitMsg[1];
      Object[] params = null;

      if (splitMsg.length > 2) {
        params = splitMsg[2].split("&");
      }
      
      if(out.verifClef(clef)) {
        out.afficherMsg(clef, params);
      }

      if(typeMessage.equals("ACTION")) {
        String nom = in.saisieJoueur();
        client.envoyer(nom);
      }
    }

    client.deconnexion();
  }

}
