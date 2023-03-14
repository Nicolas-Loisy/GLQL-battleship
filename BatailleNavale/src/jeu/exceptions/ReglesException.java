package jeu.exceptions;

/**
 * Exception lancée lorsqu'une règle du jeu n'est pas respectée.
 */
public class ReglesException extends Exception {

    private TypeException errorCode;

    /**
     * Constructeur de l'exception.
     *
     * @param errorCode le code d'erreur associé à l'exception
     */
    public ReglesException(TypeException errorCode) {
        super(getErrorMessage(errorCode));
        this.errorCode = errorCode;
    }

    /**
     * Getter pour le code d'erreur associé à l'exception.
     *
     * @return le code d'erreur associé à l'exception
     */
    public TypeException getErrorCode() {
        return errorCode;
    }

    /**
     * Méthode privée pour récupérer le message d'erreur associé à un code d'erreur.
     *
     * @param errorCode le code d'erreur associé
     * @return le message d'erreur associé
     */
    private static String getErrorMessage(TypeException errorCode) {
        return errorCode.getMessage();
    }
}
