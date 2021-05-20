package br.com.reducicla.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor

public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Endereço obrigatório")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Endereco> listaDeStrings;

}
