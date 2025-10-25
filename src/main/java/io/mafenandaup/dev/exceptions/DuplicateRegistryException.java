package io.mafenandaup.dev.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;


public class DuplicateRegistryException extends RuntimeException {
    public DuplicateRegistryException() {
        super("Já existe um registro com os campos inseridos.");
    }
    public DuplicateRegistryException(String message) {
        super(message);
    }
}
