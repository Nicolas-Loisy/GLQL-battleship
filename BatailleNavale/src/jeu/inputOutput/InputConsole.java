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

  public String getNomJoueur() throws ReglesException {
    String nom = scanner.nextLine();

    if (nom.length() > 0)
      return nom;
    else
      throw new ReglesException(TypeException.NOM_ERROR);
  }

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

