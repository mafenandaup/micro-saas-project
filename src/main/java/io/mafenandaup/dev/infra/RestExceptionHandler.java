package io.mafenandaup.dev.infra;

import io.mafenandaup.dev.exceptions.DuplicateRegistryException;
import io.mafenandaup.dev.exceptions.InvalidArgsException;
import io.mafenandaup.dev.exceptions.InvalidEnumException;
import io.mafenandaup.dev.exceptions.InvalidRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DuplicateRegistryException.class)
    private ResponseEntity<String> registroDuplicadoHandler(DuplicateRegistryException e){
return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um registro com os campos inseridos. ");
    }

    @ExceptionHandler(InvalidArgsException.class)
    private ResponseEntity<String> invalidArgsHandler(InvalidArgsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Tipos conflitantes ou limites excedidos em seu registro. Tente novamente ");
    }

    @ExceptionHandler(InvalidEnumException.class)
    private ResponseEntity<String> invalidArgsHandler(InvalidEnumException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("O valor referente ao estado do pedido é inválido. Tente novamente.");
    }

    @ExceptionHandler(InvalidRoleException.class)
    private ResponseEntity<String> invalidArgsHandler(InvalidRoleException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("O valor referente ao cargo do usuário é inválido. Tente novamente.");
    }
}
