package br.com.reducicla.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Lucas Copque on 01/06/2021
 */

/**
 * Classe responsável por buildar o objeto Chart que irá como resposta para aplicação front-end
 */

@Getter
@Setter
public class ChartResponseDTO {

    private String label;
    private Long value;

    public ChartResponseDTO() {
    }

    public ChartResponseDTO(String label, Long value) {
        this.label = label;
        this.value = value;
    }
}
