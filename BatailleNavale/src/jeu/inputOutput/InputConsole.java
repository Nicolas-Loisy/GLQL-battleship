package jeu.inputOutput;

import java.util.Scanner;

import jeu.Orientation;
import jeu.exceptions.TypeException;
import jeu.exceptions.ReglesException;

/**
 * Cette classe représente l'interface utilisateur pour la saisie des données
 */
public class InputConsole implements iInput {

  static Scanner scanner = new Scanner(System.in);

  /**
   * Récupère le nom du joueur à partir de la saisie utilisateur
   * @return Le nom du joueur
   * @throws ReglesException si le nom est vide
   */
  public String getNomJoueur() throws ReglesException {
    String nom = scanner.nextLine();

    if (nom.length() > 0)
      return nom;
    else
      throw new ReglesException(TypeException.NOM_ERROR);
  }

  /**
   * Récupère une coordonnée de type algébrique à partir de la saisie utilisateur
   * @return La coordonnée sous forme de chaîne de caractères
   * @throws ReglesException si la chaîne ne correspond pas à une coordonnée valide
   */
  public String getCoordonnee() throws ReglesException {
    String input = scanner.nextLine().toUpperCase();

    // Vérifie qu'il y a au moins une lettre et un chiffre
    if (!input.matches("(?=.*[a-zA-Z])(?=.*\\d).+")) {
      throw new ReglesException(TypeException.STRCOORD_MINIMUM_ERROR);
    }

    // Vérifie que la chaîne commence par toutes les lettres écrites à la suite
    if (!input.matches("^[a-zA-Z]+[0-9]+$")) {
      throw new ReglesException(TypeException.LETTRES_CONSECUTIVES_ERROR);
    }

    // Vérifie que la chaîne se termine par tous les chiffres écrits à la suite
    if (!input.matches("^[a-zA-Z]+\\d+$")) {
      throw new ReglesException(TypeException.CHIFFRES_CONSECUTIVES_ERROR);
    }

    return input;
  }

  /**
   * Récupère l'orientation choisie par l'utilisateur (HORIZONTAL ou VERTICAL)
   * @return L'orientation choisie
   * @throws ReglesException si l'utilisateur entre une orientation invalide
   */
  public Orientation getOrientation() throws ReglesException {
    String input = scanner.nextLine().toUpperCase();
    if (input.equals("H")) {
      return Orientation.HORIZONTAL;
    } else if (input.equals("V")) {
      return Orientation.VERTICAL;
    } else {
      throw new ReglesException(TypeException.ORIENTATION_ERROR);
    }
  }
}

