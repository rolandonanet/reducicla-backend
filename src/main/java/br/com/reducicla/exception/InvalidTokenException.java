package br.com.reducicla.exception;

/**
 * @author User on 19/05/2021
 */

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String message) {
        super(message);
    }
}