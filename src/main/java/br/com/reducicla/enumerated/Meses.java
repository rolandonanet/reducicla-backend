package br.com.reducicla.enumerated;

import lombok.Getter;

/**
 * @author Lucas Copque on 01/06/2021
 */

/**
 * Enum com meses para build do chart timeline
 */

@Getter
public enum Meses {
    JANEIRO(0, "Janeiro"),
    FEVEREIRO(1, "Fevereiro"),
    MARCO(2, "Março"),
    ABRIL(3, "Abril"),
    MAIO(4, "Maio"),
    JUNHO(5, "Junho"),
    JULHO(6, "Julho"),
    AGOSTO(7, "Agosto"),
    SETEMBRO(8, "Setembro"),
    OUTUBRO(9, "Outubro"),
    NOVEMBRO(10, "Novembro"),
    DEZEMBRO(11, "Dezembro");

    private int id;
    private String descricao;

    Meses(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
