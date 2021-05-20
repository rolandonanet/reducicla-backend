package br.com.reducicla.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor

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

}
