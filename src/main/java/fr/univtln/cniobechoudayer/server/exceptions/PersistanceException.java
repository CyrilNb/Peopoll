package fr.univtln.cniobechoudayer.server.exceptions;

/**
 * Created by Cyril on 24/10/2017.
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
