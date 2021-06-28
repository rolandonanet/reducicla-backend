package br.com.reducicla.dto.request;

import br.com.reducicla.enumerated.Role;
import br.com.reducicla.model.Endereco;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

/**
 * @author Lucas Copque on 26/05/2021
 */

/**
 * Classe responsável por realizar o binding do objeto Usuário vindo de uma requisição
 */

@Getter
@Setter
public class UsuarioRequestDTO {

    private String nome;
    private String sobrenome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;
    private Role role;
    private Endereco endereco;

    public UsuarioRequestDTO() {
    }
}
