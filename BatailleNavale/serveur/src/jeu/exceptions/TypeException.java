package jeu.exceptions;


public enum TypeException {
  
  NOM_ERROR("Le nom doit comporter au moins 1 caractère."),
  NOM_CREATION_ERROR("Un nom est requis."),
  ORIENTATION_ERROR("L'orientation saisie est invalide."),
  STRCOORD_MINIMUM_ERROR("La coordonnée doit contenir au moins une lettre et un chiffre."),
  LETTRES_CONSECUTIVES_ERROR("Les lettres doivent être écrites consécutivement au début de la coordonnée."),
  CHIFFRES_CONSECUTIVES_ERROR("Les chiffres doivent être écrits consécutivement à la fin de la coordonnée."),
  TAILLE_BATEAU_ERROR("Le nombre de coordonnées ne correspond pas à la taille du bateau."),
  HORS_PLATEAU_ERROR("Coordonnées hors du plateau."),
  COORD_OCCUPEE_ERROR("Emplacement déjà utilisé par un autre bateau."),
  COORDONNEES_ADJACENTES_ERROR("Les coordonnées du bateau ne sont pas adjacentes en ligne ou en colonne.");

  private String message;

  private TypeException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
