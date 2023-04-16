package inputOutput;


/**
 * Cette interface représente une sortie de jeu.
 */
public interface iOutput {
  public void afficherMsg(String clef, Object[] params);
  public boolean verifClef(String clef);
}

