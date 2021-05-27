package br.com.reducicla.handler;

import br.com.reducicla.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

/**
 * @author User on 19/05/2021
 */

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetails details = ErrorDetails.
                Builder.
                create().
                timestamp(new Date().getTime()).
                status(HttpStatus.NOT_FOUND.value()).
                title("Recurso Não Encontrado.").
                message(ex.getMessage()).
                locale(ex.getClass().getName()).
                build();
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Map<String, String> constraintCodeMap = new HashMap<String, String>() {
            {
                put("user_has_unique_email", "E-mail já cadastrado.");
            }
        };

        Optional<Map.Entry<String, String>> entry = constraintCodeMap.entrySet().stream()
                .filter((it) -> Objects.requireNonNull(ex.getRootCause()).getMessage().contains(it.getKey()))
                .findAny();

        ErrorDetails errorDetails = ErrorDetails.
                Builder.
                create().
                timestamp(new Date().getTime()).
                status(HttpStatus.INTERNAL_SERVER_ERROR.value()).
                title("Erro de Constraint").
                message(entry.map(Map.Entry::getValue).orElse(null)).
                locale(ex.getClass().getName()).
                build();

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}