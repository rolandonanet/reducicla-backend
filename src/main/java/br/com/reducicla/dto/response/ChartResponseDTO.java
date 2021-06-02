package br.com.reducicla.dto.response;

import lombok.Getter;

/**
 * @author Lucas Copque on 01/06/2021
 */

@Getter
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
