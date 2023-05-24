package jeu.bateaux;

/**
 * L'énumération TypeBateaux définit les différents types de bateaux disponibles pour le jeu.
 * Chaque type de bateau a un nom et une taille.
 */
public enum TypeBateaux {
  CROISEUR("Croiseur", 4, 0.1),
  PORTE_AVION("Porte-Avion", 5, 0),
  SOUS_MARIN("Sous-Marin", 3, 0.5),
  TORPILLEUR("Torpilleur", 4, 0.1),
  CONTRE_TORPILLEUR("Contre-Torpilleur", 3, 0.3);

  private String nom;
  private int taille;
  private double esquive;

  private TypeBateaux(String nom, int taille, double esquive) {
    this.nom = nom;
    this.taille = taille;
    this.esquive = esquive;
  }

  public int getTaille() {
    return taille;
  }

  public String getNom() {
    return nom;
  }

  public double getEsquive(){
    return esquive;
  }
}

