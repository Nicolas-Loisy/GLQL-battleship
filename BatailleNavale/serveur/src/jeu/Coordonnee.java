package jeu;

import java.util.Objects;

import jeu.exceptions.ReglesException;
import jeu.exceptions.TypeException;

public class Coordonnee {
  private int colonne;
  private int ligne;

  public Coordonnee(int ligne, int colonne) {
    this.ligne = ligne;
    this.colonne = colonne;
  }

  public Coordonnee(String coord) throws ReglesException {
    if (!coord.matches("(?=.*[a-zA-Z])(?=.*\\d).+")) {
      throw new ReglesException(TypeException.STRCOORD_MINIMUM_ERROR);
    }

    if (!coord.matches("^[a-zA-Z]+[0-9]+$")) {
      if (coord.matches("^[a-zA-Z]+")) {
        throw new ReglesException(TypeException.LETTRES_CONSECUTIVES_ERROR);
      } else {
        throw new ReglesException(TypeException.CHIFFRES_CONSECUTIVES_ERROR);
      }
    }    

    String ligneStr = "";
    int i = 0;

    while (i < coord.length() && Character.isLetter(coord.charAt(i))) {
      ligneStr += coord.charAt(i);
      i++;
    }

    try {
      this.ligne = getLigneFromStr(ligneStr);
      this.colonne = Integer.parseInt(coord.substring(i));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Impossible d'extraire les valeurs valeurs de " + coord);
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

  @Override
  public int hashCode() {
    return Objects.hash(ligne, colonne);
  }

  public int getLigne() {
    return this.ligne;
  }

  public int getColonne() {
    return this.colonne;
  }

}
