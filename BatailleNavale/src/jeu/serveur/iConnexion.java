package jeu.serveur;

import java.io.IOException;
import java.net.Socket;

public interface iConnexion {
    public Socket connexion() throws IOException;
    public boolean deconnexion() throws IOException;
    public void envoyer(String message) throws IOException;
    public String recevoir() throws IOException;
}
