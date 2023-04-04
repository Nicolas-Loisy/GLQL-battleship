package jeu.exceptions;

/**
 * Exception lancée lorsqu'une règle du jeu n'est pas respectée.
 */
public class ReglesException extends Exception {

    private TypeException errorCode;

    public ReglesException(TypeException errorCode) {
        super(getErrorMessage(errorCode));
        this.errorCode = errorCode;
    }

    public TypeException getErrorCode() {
        return errorCode;
    }

    private static String getErrorMessage(TypeException errorCode) {
        return errorCode.getMessage();
    }
}
