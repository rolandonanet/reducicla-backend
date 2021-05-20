package br.com.reducicla.model;

import br.com.reducicla.enumerated.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table
@Getter
@Setter

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataCadastro;

    @NotEmpty(message = "Nome obrigatório")
    private String nome;

    @NotEmpty(message = "Sobrenome obrigatório")
    private String sobrenome;

    @NotEmpty(message = "Email obrigatório")
    private String email;

    @NotEmpty(message = "Senha obrigatório")
    private String senha;

    @NotEmpty(message = "Role obrigatório")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario() {
        this.dataCadastro = new Date();
    }
}
