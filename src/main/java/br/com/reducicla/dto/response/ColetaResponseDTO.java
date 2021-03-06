package br.com.reducicla.dto.response;

import br.com.reducicla.enumerated.TipoMaterial;
import br.com.reducicla.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas Copque on 29/05/2021
 */

/**
 * Classe responsável por buildar o objeto Coleta que irá como resposta para aplicação front-end
 */

@Getter
@Setter
public class ColetaResponseDTO {

    private Long id;
    private Date dataColeta;
    private ColetorResponseDTO coletor;
    private ColaboradorResponseDTO colaborador;
    private List<MaterialResponseDTO> materiais;

    public ColetaResponseDTO(Coleta coleta) {
        this.id = coleta.getId();
        this.dataColeta = coleta.getDataColeta();
        this.coletor = new ColetorResponseDTO(coleta.getColetor());
        this.colaborador = new ColaboradorResponseDTO(coleta.getColaborador());
        this.materiais = coleta.getMateriais().stream().map(MaterialResponseDTO::new).collect(Collectors.toList());
    }

    @Getter
    private class ColetorResponseDTO {
        private Long id;
        private String nome;
        private String sobrenome;

        public ColetorResponseDTO(Coletor coletor) {
            this.id = coletor.getId();
            this.nome = coletor.getNome();
            this.sobrenome = coletor.getSobrenome();
        }
    }

    @Getter
    private class ColaboradorResponseDTO {
        private Long id;
        private String nome;
        private String sobrenome;
        private Endereco endereco;

        public ColaboradorResponseDTO(Colaborador colaborador) {
            this.id = colaborador.getId();
            this.nome = colaborador.getNome();
            this.sobrenome = colaborador.getSobrenome();
            this.endereco = colaborador.getEndereco();
        }
    }

    @Getter
    private class MaterialResponseDTO {
        private Long id;
        private String nome;
        private Integer quantidade;
        private TipoMaterial tipo;

        public MaterialResponseDTO(Material material) {
            this.id = material.getId();
            this.nome = material.getNome();
            this.quantidade = material.getQuantidade();
            this.tipo = material.getTipo();
        }
    }
}
