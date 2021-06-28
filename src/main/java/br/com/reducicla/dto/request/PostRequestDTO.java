package br.com.reducicla.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucas Copque on 26/05/2021
 */

/**
 * Classe responsável por realizar o binding do objeto Post vindo de uma requisição
 */

@Getter
@Setter
public class PostRequestDTO {

    private String titulo;
    private String descricao;
    private String imageUrl;

    public PostRequestDTO() {
    }
}
