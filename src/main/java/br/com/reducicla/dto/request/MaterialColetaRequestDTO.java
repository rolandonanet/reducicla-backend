package br.com.reducicla.dto.request;

import br.com.reducicla.enumerated.TipoMaterial;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucas Copque on 29/05/2021
 */

@Setter
@Getter
public class MaterialColetaRequestDTO {

    private Long id;
    private String nome;
    private Integer quantidade;
    private TipoMaterial tipo;

    public MaterialColetaRequestDTO() {

    }
}
