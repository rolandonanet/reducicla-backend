package br.com.reducicla.model;

import br.com.reducicla.dto.request.UsuarioRequestDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author User on 19/05/2021
 */

@Entity
@DynamicUpdate
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "usuario_coletor")
@PrimaryKeyJoinColumn(name = "usuario_id")

public class Coletor extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "coletor")
    @JsonManagedReference
    private List<Coleta> coletas;

    public Coletor() {
        this.setDataCadastro(new Date());
    }

    public Coletor(UsuarioRequestDTO usuarioRequestDTO) {
        this.setDataCadastro(new Date());
        this.setNome(usuarioRequestDTO.getNome());
        this.setSobrenome(usuarioRequestDTO.getSobrenome());
        this.setEmail(usuarioRequestDTO.getEmail());
        this.setSenha(usuarioRequestDTO.getSenha());
        this.setRole(usuarioRequestDTO.getRole());
        this.setEndereco(usuarioRequestDTO.getEndereco());
    }
}
