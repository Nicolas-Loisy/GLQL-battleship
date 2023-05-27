package jeu.bateaux;

public class Bateau {

  private String nom;
  private int taille;
  private double esquive;

  public Bateau(String nom, int taille, double esquive) {
    this.nom = nom;
    this.taille = taille;
    this.esquive = esquive;
  }

  public int getTaille() {
    return this.taille;
  }

  public String getNom() {
    return nom;
  }

  public double getEsquive() {
    return esquive;
  }

  /**
   * Cette méthode retourne une représentation en chaîne de caractères du bateau.
   * Elle retourne le nom du bateau avec la première lettre de chaque mot en majuscule.
   * @return une représentation en chaîne de caractères du bateau.
   */
  @Override
  public String toString() {
    if (nom == null || nom.isEmpty()) {
      return "";
    } else {
      StringBuilder resultat = new StringBuilder();
      String[] mots = nom.split("[ -]");
      
      for (String m : mots) {
        if (!m.isEmpty()) {
          resultat.append(Character.toUpperCase(m.charAt(0)));
        }
      }

      return resultat.toString();
    }
  }
}
