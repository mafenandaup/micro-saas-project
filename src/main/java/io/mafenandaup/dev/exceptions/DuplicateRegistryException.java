package io.mafenandaup.dev.exceptions;

public class DuplicateRegistryException extends RuntimeException {
    public DuplicateRegistryException(String message) {
        super(message);
    }
}
