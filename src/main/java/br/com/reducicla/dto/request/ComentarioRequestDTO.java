package br.com.reducicla.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Setter
@Getter
public class ComentarioRequestDTO {

    private String nome;
    private String texto;

    public ComentarioRequestDTO() {
    }
}
