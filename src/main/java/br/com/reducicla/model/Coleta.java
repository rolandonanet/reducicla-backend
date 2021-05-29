package br.com.reducicla.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
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
    @JsonBackReference
    private Coletor coletor;

    @ManyToOne
    @JsonBackReference
    private Colaborador colaborador;

    @ManyToOne
    @JsonBackReference
    private PontoColeta pontoColeta;

    @OneToMany(mappedBy = "coleta")
    @JsonManagedReference
    private List<Material> materiais;

    public Coleta() {
        this.dataColeta = new Date();
    }

    public Coleta(Coletor coletor, Colaborador colaborador, PontoColeta pontoColeta) {
        this.dataColeta = new Date();
        this.coletor = coletor;
        this.colaborador = colaborador;
        this.pontoColeta = pontoColeta;
        this.materiais = new ArrayList<>();
    }
}
