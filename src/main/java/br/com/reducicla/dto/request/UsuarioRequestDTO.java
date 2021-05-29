package br.com.reducicla.dto.request;

import br.com.reducicla.enumerated.Role;
import br.com.reducicla.model.Endereco;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author User on 26/05/2021
 */

@ToString
@Getter
@Setter
public class UsuarioRequestDTO {

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private Role role;
    private Endereco endereco;

    public UsuarioRequestDTO() {
    }
}
