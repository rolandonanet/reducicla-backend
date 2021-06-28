package br.com.reducicla.dto.request;

import br.com.reducicla.enumerated.TipoMaterial;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucas Copque on 26/05/2021
 */

/**
 * Classe responsável por realizar o binding do objeto Material vindo de uma requisição
 */

@Getter
@Setter
public class MaterialRequestDTO {
    private String nome;
    private Integer quantidade;
    private TipoMaterial tipo;

    public MaterialRequestDTO() {
    }
}
