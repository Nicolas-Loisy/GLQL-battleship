package jeu;

/**
 * Énumération représentant les différents résultats possibles d'une attaque sur un bateau.
 * MANQUE : L'attaque a raté le navire.
 * TOUCHE : L'attaque a touché un navire, mais ne l'a pas coulé.
 * COULE : L'attaque a coulé un navire.
 * GAMEOVER : Tous les navires ont été coulés, le jeu est terminé.
 */
public enum ResultatAttaque {
  MANQUE,
  TOUCHE,
  COULE,
  GAMEOVER
}
