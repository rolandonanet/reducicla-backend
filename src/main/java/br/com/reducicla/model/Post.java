package br.com.reducicla.model;

import br.com.reducicla.dto.request.PostRequestDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Gustavo Miguel on 19/05/2021
 */

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

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "post")
    @JsonManagedReference
    private List<Comentario> comentarios;

    public Post() {
        this.dataCadastro = new Date();
    }

    public Post(PostRequestDTO postRequestDTO) {
        this.imageUrl = postRequestDTO.getImageUrl();
        this.dataCadastro = new Date();
        this.titulo = postRequestDTO.getTitulo();
        this.descricao = postRequestDTO.getDescricao();
    }
}
