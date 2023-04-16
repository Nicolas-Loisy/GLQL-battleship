package jeu.bateaux;

/**
 * La classe Bateau représente un bateau avec un nom et une taille.
 * Elle implémente l'interface Bateau.
 */
public class Bateau {

  private String nom;
  private int taille;

  public Bateau(String nom, int taille) {
    this.nom = nom;
    this.taille = taille;
  }

  public int getTaille() {
    return this.taille;
  }

  public String getNom() {
    return nom;
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
