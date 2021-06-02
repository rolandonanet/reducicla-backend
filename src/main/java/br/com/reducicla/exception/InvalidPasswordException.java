package br.com.reducicla.exception;

/**
 * @author Lucas Copque on 30/05/2021
 */
public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}