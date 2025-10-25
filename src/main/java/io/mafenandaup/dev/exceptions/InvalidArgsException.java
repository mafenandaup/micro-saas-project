package io.mafenandaup.dev.exceptions;

public class InvalidArgsException extends RuntimeException {

    public InvalidArgsException() {
        super("Tipo incorreto ou limite excedido em seu registro. Tente novamente");
    }

    public InvalidArgsException(String message) {
        super(message);
    }
}
