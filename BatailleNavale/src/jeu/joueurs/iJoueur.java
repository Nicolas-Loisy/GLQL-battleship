package jeu.joueurs;

import java.util.List;
import java.util.Map;

import jeu.Coordonnee;
import jeu.Orientation;
import jeu.Plateau;
import jeu.ResultatAttaque;
import jeu.bateaux.Bateau;

public interface iJoueur {
  /**
   * Retourne le nom du joueur.
   *
   * @return Le nom du joueur.
   */
  public String getNom();

  /**
   * Retourne le plateau de jeu du joueur.
   *
   * @return Le plateau de jeu du joueur.
   */
  public Plateau getPlateau();

  /**
   * Retourne une carte des attaques effectuées par le joueur sur le plateau
   * adverse.
   * La carte associe chaque coordonnée d'attaque à un booléen indiquant si
   * l'attaque a touché un bateau.
   *
   * @return Une carte des attaques effectuées par le joueur sur le plateau
   *         adverse.
   */
  public Map<Coordonnee, Boolean> getAttaques();

  /**
   * Effectue une attaque sur le plateau adverse à partir d'une coordonnée donnée.
   * Retourne le résultat de l'attaque (touché, coulé, ou raté).
   *
   * @param plateauAdversaire Le plateau de jeu de l'adversaire.
   * @param coordonnee        La coordonnée à attaquer.
   * @return Le résultat de l'attaque.
   */
  public ResultatAttaque attaquer(Plateau plateauAdversaire, Coordonnee coordonnee);

  /**
   * Demande le nom de l'utilisateur et retourne la saisie sous forme de chaîne de
   * caractères.
   * 
   * @return Le nom de l'utilisateur saisi.
   */
  public String askUserNom();

  /**
   * Demande à l'utilisateur de saisir une coordonnée et retourne la saisie sous
   * forme d'objet Coordonnee.
   * 
   * @return Une instance de la classe Coordonnee représentant la coordonnée
   *         saisie par l'utilisateur.
   */
  public Coordonnee askUserCoordonnee();

  /**
   * Demande à l'utilisateur de saisir une orientation et retourne la saisie sous
   * forme d'objet Orientation.
   * 
   * @return Une instance de la classe Orientation représentant l'orientation
   *         saisie par l'utilisateur.
   */
  public Orientation askUserOrientation();

  /**
   * Place les bateaux passés en paramètre sur la grille de jeu.
   * 
   * @param bateauxAPlacer La liste des bateaux à placer sur la grille de jeu.
   */
  public void placerBateaux(List<Bateau> bateauxAPlacer);

}
