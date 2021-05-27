package br.com.reducicla.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Getter
@Setter
public class PostRequestDTO {

    private String titulo;
    private String descricao;

    public PostRequestDTO() {
    }
}
