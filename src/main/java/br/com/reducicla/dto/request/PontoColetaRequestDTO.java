package br.com.reducicla.dto.request;

import br.com.reducicla.model.Endereco;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Setter
@Getter
public class PontoColetaRequestDTO {

    private Endereco endereco;

    public PontoColetaRequestDTO() {
    }
}
