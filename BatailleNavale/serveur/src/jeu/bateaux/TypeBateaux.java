package jeu.bateaux;

/**
 * L'énumération TypeBateaux définit les différents types de bateaux disponibles pour le jeu.
 * Chaque type de bateau a un nom et une taille.
 */
public enum TypeBateaux {
  
  CROISEUR("Croiseur", 4),
  PORTE_AVION("Porte-Avion", 5),
  SOUS_MARIN("Sous-Marin", 3),
  TORPILLEUR("Torpilleur", 4),
  CONTRE_TORPILLEUR("Contre-Torpilleur", 5);

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

