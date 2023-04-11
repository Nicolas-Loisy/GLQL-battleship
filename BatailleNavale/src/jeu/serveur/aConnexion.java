package jeu.serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class aConnexion implements iConnexion {
    private int port;
    private Socket socket;
    private BufferedReader in; // flux de réception de données
    private PrintWriter out; // flux d'envoi de données

    protected aConnexion(int port){
        this.port = port;
    }
    
    protected Socket getSocket(){
        return this.socket;
    }

    protected void setSocket(Socket socket){
        this.socket = socket;
    }
    
    protected int getPort() {
        return this.port;
    }
    
    protected PrintWriter getOut() {
        return out;
    }
    
    protected void setOut(PrintWriter out) {
        this.out = out;
    }
    
    protected BufferedReader getIn() {
        return in;
    }
    
    protected void setIn(BufferedReader in) {
        this.in = in;
    }

    public void envoyer(String message) throws IOException {
        out.println(message);
        out.flush();
    }
    
    public String recevoir() throws IOException {
        return in.readLine();
    }
}
