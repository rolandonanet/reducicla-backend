package br.com.reducicla.exception;

/**
 * @author User on 19/05/2021
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}