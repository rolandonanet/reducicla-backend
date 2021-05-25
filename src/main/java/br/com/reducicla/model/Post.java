package br.com.reducicla.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    private String titulo;

    private String descricao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    public Post() {
        this.dataCadastro = new Date();
    }

}
