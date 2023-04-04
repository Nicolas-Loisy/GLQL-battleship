package jeu.bateaux;

/**
 * L'énumération TypeBateaux définit les différents types de bateaux disponibles pour le jeu.
 * Chaque type de bateau a un nom et une taille.
 */
public enum TypeBateaux {
  
  CROISEUR("croiseur", 4),
  PORTE_AVION("porte-avion", 5),
  SOUS_MARIN("sous-marin", 3),
  TORPILLEUR("torpilleur", 4),
  CONTRE_TORPILLEUR("contre-torpilleur", 5);

  private String nom;
  private int taille;

  private TypeBateaux(String nom, int taille) {
    this.nom = nom;
    this.taille = taille;
  }

  public int getTaille() {
    return taille;
  }

  public String getNom() {
    return nom;
  }
}

