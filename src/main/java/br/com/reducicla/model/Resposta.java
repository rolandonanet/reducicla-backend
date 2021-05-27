package br.com.reducicla.model;

import br.com.reducicla.dto.request.RespostaRequestDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Entity
@DynamicUpdate
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "resposta")
public class Resposta {

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
    private Comentario comentario;

    public Resposta() {
    }

    public Resposta(RespostaRequestDTO respostaRequestDTO, Comentario comentario){
        this.nome = respostaRequestDTO.getNome();
        this.texto = respostaRequestDTO.getTexto();
        this.comentario = comentario;
    }
}
