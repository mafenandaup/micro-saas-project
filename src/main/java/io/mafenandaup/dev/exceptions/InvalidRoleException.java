package io.mafenandaup.dev.exceptions;

public class InvalidRoleException extends RuntimeException {

    public InvalidRoleException() {
        super("O valor referente ao cargo do usuário é inválido. Tente novamente.");
    }
    public InvalidRoleException(String message) {
        super(message);
    }
}
