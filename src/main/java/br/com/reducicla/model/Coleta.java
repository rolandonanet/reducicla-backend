package br.com.reducicla.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "coleta")
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataColeta;

    @ManyToOne
    private PontoColeta pontoColeta;

    @ManyToOne
    private Colaborador colaborador;

    @ManyToOne
    private Coletor coletor;

    @OneToMany
    private List<Material> materiais;

    public Coleta() {
        this.dataColeta = new Date();
    }

}
