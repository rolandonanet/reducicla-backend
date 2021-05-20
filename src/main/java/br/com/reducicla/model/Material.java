package br.com.reducicla.model;

import br.com.reducicla.enumerated.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor

public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome obrigatório")
    private String nome;

    @NotEmpty(message = "Quantidade obrigatório")
    private Integer quantidade;

    @NotEmpty(message = "Material obrigatório")
    @Enumerated(EnumType.STRING)
    private Role role;

}
