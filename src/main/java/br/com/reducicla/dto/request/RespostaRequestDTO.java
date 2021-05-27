package br.com.reducicla.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Setter
@Getter
public class RespostaRequestDTO {

    private String nome;
    private String texto;

    public RespostaRequestDTO() {
    }
}
