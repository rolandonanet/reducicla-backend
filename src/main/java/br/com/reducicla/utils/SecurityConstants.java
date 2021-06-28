package br.com.reducicla.utils;

/**
 * @author Lucas Copque on 19/05/2021
 */

/**
 * Classe com constantes utilizadas para gerar o JWT
 */

public class SecurityConstants {
    public static final Long TIME_EXPIRATION = 2592000000L;
    public static final String SECRET_KEY = "XlDsBT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
}