package jeu.bateaux;

/**
 * La classe Bateau représente un bateau avec un nom et une taille.
 * Elle implémente l'interface Bateau.
 */
public class Bateau {

  private String nom;
  private int taille;

  /**
   * Constructeur de la classe Bateau.
   * @param nom    le nom du bateau.
   * @param taille la taille du bateau.
   */
  public Bateau(String nom, int taille) {
    this.nom = nom;
    this.taille = taille;
  }

  /**
   * Cette méthode renvoie la taille du bateau.
   * @return la taille du bateau.
   */
  public int getTaille() {
    return this.taille;
  }

  /**
   * Cette méthode renvoie le nom du bateau.
   * @return le nom du bateau.
   */
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
