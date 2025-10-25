package io.mafenandaup.dev.exceptions;

public class InvalidEnumException extends RuntimeException {

    public InvalidEnumException() {
        super("O valor referente ao estado do pedido é inválido. Tente novamente.");
    }
    public InvalidEnumException(String message) {
        super(message);
    }
}
