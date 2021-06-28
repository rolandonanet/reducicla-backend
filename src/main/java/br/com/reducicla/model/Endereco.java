package br.com.reducicla.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @author Gustavo Miguel on 19/05/2021
 */

@Entity
@DynamicUpdate
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Logradouro obrigatório")
    private String logradouro;

    private String numero;

    private String complemento;

    @NotEmpty(message = "Bairro obrigatório")
    private String bairro;

    @NotEmpty(message = "Cidade obrigatório")
    private String cidade;

    @NotEmpty(message = "Estado obrigatório")
    private String estado;

    @NotEmpty(message = "CEP obrigatório")
    private String cep;

    private Integer lat;

    private Integer lng;

    public Endereco() {
    }
}
