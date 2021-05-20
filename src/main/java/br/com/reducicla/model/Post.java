package br.com.reducicla.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    private String titulo;

    private String descricao;

    private Integer curtidas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> comentarios;

    public Post(){
        this.dataCadastro = new Date();
    }

}
