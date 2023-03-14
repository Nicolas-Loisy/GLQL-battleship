package jeu;

import java.util.Objects;

public class Coordonnee {
  private int colonne;
  private int ligne;

  /**
   * Constructeur de la classe Coordonnee.
   * 
   * @param ligne   la coordonnée de ligne.
   * @param colonne la coordonnée de colonne.
   */
  public Coordonnee(int ligne, int colonne) {
    this.ligne = ligne;
    this.colonne = colonne;
  }

  /**
   * Crée une nouvelle coordonnée à partir d'une chaîne de caractères.
   * La chaîne doit être de la forme "A1" ou "B10", c'est-à-dire une lettre suivie
   * d'un nombre.
   * Les lettres de A à Z représentent les lignes du plateau
   * Les nombres représentent les colonnes.
   * 
   * @param coord la chaîne de caractères à partir de laquelle créer la
   *              coordonnée.
   * @throws IllegalArgumentException si la chaîne ne correspond pas au format
   *                                  attendu.
   */
  public Coordonnee(String coord) {
    if (coord == null || coord.length() < 2) {
      throw new IllegalArgumentException("Coordonnée invalide : " + coord);
    }
    String ligneStr = "";
    int i = 0;
    while (i < coord.length() && Character.isLetter(coord.charAt(i))) {
      ligneStr += coord.charAt(i);
      i++;
    }
    try {
      this.ligne = getLigneFromStr(ligneStr); // La lettre représente la ligne
      this.colonne = Integer.parseInt(coord.substring(i)); // Le nombre représente la colonne
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Coordonnée invalide : " + coord);
    }
  }

  /**
   * Convertit une chaîne de caractères en entier représentant la ligne
   * correspondante.
   * La méthode se base sur le fait que les lettres de A à Z représentent les
   * lignes du plateau,
   * avec A=1, B=2, ..., Z=26.
   * 
   * @param ligneStr la chaîne de caractères à convertir en entier.
   * @return l'entier correspondant à la valeur de la ligne.
   */
  private static int getLigneFromStr(String ligneStr) {
    int ligne = 0;
    ligneStr = ligneStr.toUpperCase(); // convertit en majuscules pour simplifier
    for (int i = 0; i < ligneStr.length(); i++) {
      char c = ligneStr.charAt(ligneStr.length() - 1 - i);
      if (c < 'A' || c > 'Z') {
        throw new IllegalArgumentException("Coordonnée invalide : " + ligneStr);
      }
      ligne += (c - 'A' + 1) * Math.pow(26, i);
    }
    return ligne;
  }

  /**
   * Convertit la coordonnée en une chaîne de caractères de la forme "A1" ou
   * "B10".
   * 
   * @return la chaîne de caractères correspondant à la coordonnée.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int tempLigne = this.ligne;
    while (tempLigne > 0) {
      int mod = (tempLigne - 1) % 26;
      sb.insert(0, (char) (mod + 'A'));
      tempLigne = (tempLigne - mod - 1) / 26;
    }
    sb.append(this.colonne);
    return sb.toString();
  }

  /**
   * Vérifie si l'objet spécifié est égal à cette instance de Coordonnee.
   * Deux Coordonnee sont considérées comme égales si elles ont la même colonne et
   * la même ligne.
   *
   * @param obj l'objet à comparer à cette instance de Coordonnee.
   * @return true si l'objet est égal à cette instance de Coordonnee, false sinon.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || !(obj instanceof Coordonnee)) {
      return false;
    }
    Coordonnee other = (Coordonnee) obj;
    return this.ligne == other.ligne && this.colonne == other.colonne;
  }

  /**
   * Calcule le code de hachage de cette coordonnée.
   *
   * @return le code de hachage de cette coordonnée.
   */
  @Override
  public int hashCode() {
    return Objects.hash(ligne, colonne);
  }

  /**
   * Renvoie la ligne associée à cette instance de Coordonnee.
   *
   * @return la ligne associée à cette instance de Coordonnee.
   */
  public int getLigne() {
    return this.ligne;
  }

  /**
   * Renvoie la colonne associée à cette instance de Coordonnee.
   *
   * @return la colonne associée à cette instance de Coordonnee.
   */
  public int getColonne() {
    return this.colonne;
  }

}
