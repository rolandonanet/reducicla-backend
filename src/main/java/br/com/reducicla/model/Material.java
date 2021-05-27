package br.com.reducicla.model;

import br.com.reducicla.enumerated.TipoMaterial;
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

    @NotEmpty(message = "Quantidade obrigatório")
    private Integer quantidade;

    @ManyToOne
    private Colaborador colaborador;

    @NotEmpty(message = "Tipo do material obrigatório")
    @Enumerated(EnumType.STRING)
    private TipoMaterial tipo;

    public Material() {
    }
}
