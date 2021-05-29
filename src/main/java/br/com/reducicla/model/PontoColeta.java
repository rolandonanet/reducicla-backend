package br.com.reducicla.model;

import br.com.reducicla.dto.request.PontoColetaRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "ponto_coleta")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean aprovado;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Endereco endereco;

    @ManyToMany
    private List<Colaborador> colaboradores;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonBackReference
    private List<Coleta> coletas;

    public PontoColeta() {
        this.dataCadastro = new Date();
        this.aprovado = false;
    }

    public PontoColeta(PontoColetaRequestDTO pontoColetaRequestDTO, Colaborador colaborador) {
        this.dataCadastro = new Date();
        this.aprovado = false;
        this.endereco = pontoColetaRequestDTO.getEndereco();
        this.colaboradores = Collections.singletonList(colaborador);
    }
}
