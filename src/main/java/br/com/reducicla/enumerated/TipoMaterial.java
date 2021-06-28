package br.com.reducicla.enumerated;

import lombok.Getter;

/**
 * Enum com os tipos de materiais
 */

@Getter
public enum TipoMaterial {
    PLASTICO("Plástico"),
    VIDRO("Vidro"),
    PAPEL("Papel"),
    METAL("Metal");

    private String label;

    TipoMaterial(String label) {
        this.label = label;
    }
}
