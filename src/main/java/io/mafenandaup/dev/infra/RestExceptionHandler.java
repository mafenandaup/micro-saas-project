package io.mafenandaup.dev.infra;

import io.mafenandaup.dev.exceptions.DuplicateRegistryException;
import io.mafenandaup.dev.exceptions.InvalidArgsException;
import io.mafenandaup.dev.exceptions.InvalidDeletionException;
import io.mafenandaup.dev.exceptions.InvalidRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DuplicateRegistryException.class)
    private ResponseEntity<RestErrorMessage> registroDuplicadoHandler(DuplicateRegistryException e){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);    }

    @ExceptionHandler(InvalidArgsException.class)
    private ResponseEntity<RestErrorMessage> invalidArgsHandler(InvalidArgsException e){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);    }

    @ExceptionHandler(InvalidDeletionException.class)
    private ResponseEntity<RestErrorMessage> invalidDeletionHandler(InvalidDeletionException e){
        RestErrorMessage errorMessage = new RestErrorMessage(HttpStatus.CONFLICT, e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleInvalidRole(HttpMessageNotReadableException ex) {
        String message = "Valor inválido para campo 'role'. VALORES PERMITIDOS:ADMIN, CLIENTE ou REPRESENTANTE.";
        return buildResponse(message, HttpStatus.BAD_REQUEST);
    }



    private ResponseEntity<Object> buildResponse(String message, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
