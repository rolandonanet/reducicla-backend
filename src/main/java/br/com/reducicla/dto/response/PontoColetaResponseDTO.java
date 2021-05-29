package br.com.reducicla.dto.response;

import br.com.reducicla.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Setter
@Getter
public class PontoColetaResponseDTO {

    private Long id;
    private Date dataCadastro;
    private Boolean aprovado;
    private Endereco endereco;
    private List<ColaboradorResponseDTO> colaboradores;
    private List<Coleta> coletas;

    public PontoColetaResponseDTO(PontoColeta pontoColeta) {
        this.id = pontoColeta.getId();
        this.dataCadastro = pontoColeta.getDataCadastro();
        this.aprovado = pontoColeta.getAprovado();
        this.endereco = pontoColeta.getEndereco();
        this.colaboradores = pontoColeta.getColaboradores().stream().map(ColaboradorResponseDTO::new).collect(Collectors.toList());
    }

    @Getter
    private class ColaboradorResponseDTO {
        private Long id;
        private String nome;
        private String sobrenome;
        private String email;
        private List<Material> materiais;

        public ColaboradorResponseDTO(Colaborador colaborador) {
            this.id = colaborador.getId();
            this.nome = colaborador.getNome();
            this.sobrenome = colaborador.getSobrenome();
            this.email = colaborador.getEmail();
            this.materiais = colaborador.getMateriais();
        }
    }
}
