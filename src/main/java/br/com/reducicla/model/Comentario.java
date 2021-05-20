package br.com.reducicla.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter

public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    private String nome;

    @NotEmpty(message = "Texto obrigatório")
    private String texto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comentario> respostas;

    public Comentario() { this.dataCadastro = new Date(); }
}
