package jeu.inputOutput;

/**
 * Cette classe représente une fabrique de classes InputOutput
 */
public class InputOutputFactory {
  /**
   * Renvoie une instance de iInput en fonction du type spécifié
   * @param type Le type d'InputOutput à créer
   * @return Une instance de iInput correspondant au type spécifié
   */
  public static iInput getInput(TypeInputOutput type) {
    switch(type) {
      case CONSOLE :
        return new InputConsole();
      case IU :
        return null;
      default :
        return null;
    }
  }

  /**
   * Renvoie une instance de iOutput en fonction du type spécifié
   * @param type Le type d'InputOutput à créer
   * @return Une instance de iOutput correspondant au type spécifié
   */
  public static iOutput getOutput(TypeInputOutput type) {
    switch(type) {
      case CONSOLE :
        return new OutputConsole();
      case IU :
        return null;
      default :
        return null;
    }
  }
}
