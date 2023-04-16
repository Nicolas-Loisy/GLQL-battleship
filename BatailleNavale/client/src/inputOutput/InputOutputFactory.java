package inputOutput;

/**
 * Cette classe représente une fabrique de classes InputOutput
 */
public class InputOutputFactory {
  public static iInput getInput(TypeInOut inOutType) {
    switch(inOutType) {
      case CONSOLE :
        return new InputConsole();
      default :
        return null;
    }
  }

  public static iOutput getOutput(TypeInOut inOutType) {
    switch(inOutType) {
      case CONSOLE :
        return new OutputConsole();
      default :
        return null;
    }
  }
}
