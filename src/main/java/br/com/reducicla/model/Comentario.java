package br.com.reducicla.model;

import br.com.reducicla.dto.request.ComentarioRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    private String nome;

    @NotEmpty(message = "Texto obrigatório")
    private String texto;

    @ManyToOne
    @JsonBackReference
    private Post post;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "comentario")
    @JsonManagedReference
    private List<Resposta> respostas;

    public Comentario() {
        this.dataCadastro = new Date();
    }

    public Comentario(ComentarioRequestDTO comentarioRequestDTO, Post post) {
        this.dataCadastro = new Date();
        this.nome = comentarioRequestDTO.getNome();
        this.texto = comentarioRequestDTO.getTexto();
        this.post = post;
    }
}
