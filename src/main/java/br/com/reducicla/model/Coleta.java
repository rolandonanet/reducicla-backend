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

public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataColeta;

    @ManyToOne
    private Coletor coletor;

    @OneToOne
    private PontoColeta pontoColeta;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Material> materiais;

    public Coleta(){
        this.dataColeta = new Date();
    }

}
