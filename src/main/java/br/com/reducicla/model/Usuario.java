package br.com.reducicla.model;

import br.com.reducicla.dto.request.UsuarioRequestDTO;
import br.com.reducicla.enumerated.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@DynamicUpdate
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "user_has_unique_email"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @NotEmpty(message = "Nome obrigatório")
    private String nome;

    @NotEmpty(message = "Sobrenome obrigatório")
    private String sobrenome;

    @NotEmpty(message = "Email obrigatório")
    private String email;

    @NotEmpty(message = "Senha obrigatório")
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario() {
        this.dataCadastro = new Date();
    }

    public Usuario(UsuarioRequestDTO usuarioRequestDTO) {
        this.setDataCadastro(new Date());
        this.setNome(usuarioRequestDTO.getNome());
        this.setSobrenome(usuarioRequestDTO.getSobrenome());
        this.setEmail(usuarioRequestDTO.getEmail());
        this.setSenha(usuarioRequestDTO.getSenha());
        this.setRole(usuarioRequestDTO.getRole());
    }
}
