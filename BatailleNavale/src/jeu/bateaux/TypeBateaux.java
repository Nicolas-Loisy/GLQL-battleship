package jeu.bateaux;

/**
 * L'énumération TypeBateaux définit les différents types de bateaux disponibles pour le jeu.
 * Chaque type de bateau a un nom et une taille.
 */
public enum TypeBateaux {
  
  /**
   * Type de bateau : croiseur
   */
  CROISEUR("croiseur", 4),

  /**
   * Type de bateau : porte-avion
   */
  PORTE_AVION("porte-avion", 5),

  /**
   * Type de bateau : sous-marin
   */
  SOUS_MARIN("sous-marin", 3),

  /**
   * Type de bateau : torpilleur
   */
  TORPILLEUR("torpilleur", 4),

  /**
   * Type de bateau : contre-torpilleur
   */
  CONTRE_TORPILLEUR("contre-torpilleur", 5);

  private String nom;
  private int taille;

  /**
   * Constructeur de l'énumération TypeBateaux.
   *
   * @param nom le nom du type de bateau.
   * @param taille la taille du type de bateau.
   */
  private TypeBateaux(String nom, int taille) {
    this.nom = nom;
    this.taille = taille;
  }

  /**
   * Cette méthode renvoie la taille du type de bateau.
   *
   * @return la taille du type de bateau.
   */
  public int getTaille() {
    return taille;
  }

  /**
   * Cette méthode renvoie le nom du type de bateau.
   *
   * @return le nom du type de bateau.
   */
  public String getNom() {
    return nom;
  }
}

