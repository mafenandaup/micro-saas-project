package io.mafenandaup.dev.exceptions;

    public class InvalidArgsException extends RuntimeException {
        public InvalidArgsException(String message) {
            super(message);
        }
    }