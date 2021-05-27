package br.com.reducicla.model;

import br.com.reducicla.dto.request.MaterialRequestDTO;
import br.com.reducicla.enumerated.TipoMaterial;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@DynamicUpdate
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "material")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome obrigatório")
    private String nome;

    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    private TipoMaterial tipo;

    @ManyToOne
    @JsonBackReference
    private Colaborador colaborador;

    public Material() {
    }

    public Material(MaterialRequestDTO materialRequestDTO, Colaborador colaborador) {
        this.nome = materialRequestDTO.getNome();
        this.quantidade = materialRequestDTO.getQuantidade();
        this.tipo = materialRequestDTO.getTipo();
        this.colaborador = colaborador;
    }
}
