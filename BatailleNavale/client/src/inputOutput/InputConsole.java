package inputOutput;

import java.util.Scanner;

/**
 * Cette classe représente l'interface utilisateur pour la saisie des données
 */
public class InputConsole implements iInput {

  static Scanner scanner = new Scanner(System.in);

  public String saisieJoueur() {
    String nom = scanner.nextLine();

    return nom;
  }

  public String getCoordonnee() {
    String input = scanner.nextLine().toUpperCase();
    return input;
  }

  @Override
  public String getOrientation() {
    String input = scanner.nextLine().toUpperCase();
    return input;
  }

}

