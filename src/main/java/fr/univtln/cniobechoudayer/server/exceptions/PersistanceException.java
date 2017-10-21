package fr.univtln.cniobechoudayer.server.exceptions;

/**
 * Cette classe représente les erreurs émises lors de la persistance.
 * L'erreur d'origine est accessible via getException().
 */
public class PersistanceException extends Exception {
    private Exception exception;

    public PersistanceException(String message) {
        super(message);
    }

    public PersistanceException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }
}
