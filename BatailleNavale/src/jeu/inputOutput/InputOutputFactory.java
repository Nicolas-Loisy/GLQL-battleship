package jeu.inputOutput;

/**
 * Cette classe représente une fabrique de classes InputOutput
 */
public class InputOutputFactory {
  public static iInput getInput(TypeInputOutput inOutType) {
    switch(inOutType) {
      case CONSOLE :
        return new InputConsole();
      case IU :
        return null;
      default :
        return null;
    }
  }

  public static iOutput getOutput(TypeInputOutput inOutType) {
    switch(inOutType) {
      case CONSOLE :
        return new OutputConsole();
      case IU :
        return null;
      default :
        return null;
    }
  }
}
