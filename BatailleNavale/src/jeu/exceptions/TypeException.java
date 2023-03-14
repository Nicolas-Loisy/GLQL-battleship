package jeu.exceptions;

/**
 * Enumération des codes d'exception pour le jeu de bataille navale.
 */
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

  /**
   * Message associé au code d'exception.
   */
  private String message;

  /**
   * Constructeur privé pour initialiser le message associé au code d'exception.
   * @param message le message d'erreur associé au code d'exception.
   */
  private TypeException(String message) {
    this.message = message;
  }

  /**
   * Getter pour récupérer le message d'erreur associé au code d'exception.
   * @return le message d'erreur associé au code d'exception.
   */
  public String getMessage() {
    return message;
  }
}
