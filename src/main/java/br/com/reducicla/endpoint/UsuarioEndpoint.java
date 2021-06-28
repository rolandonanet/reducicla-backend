package br.com.reducicla.endpoint;

import br.com.reducicla.dto.request.UsuarioRequestDTO;
import br.com.reducicla.model.Usuario;
import br.com.reducicla.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucas Copque on 26/05/2021
 */

/**
 * Endpoint mapeado à requests de usuário
 */

@RestController
@RequestMapping("v1")
public class UsuarioEndpoint {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioEndpoint(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Registra um usuário
     * @param usuarioRequestDTO Objeto que irá fazer o binding de usuário
     * @return Retorna o usuário salvo
     */
    @PostMapping("usuarios")
    public ResponseEntity<?> save(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return new ResponseEntity<>(this.usuarioService.save(usuarioRequestDTO), HttpStatus.CREATED);
    }

    /**
     * Busca um usuário pelo seu código identificador
     * @param id Long - Código identificador do usuário
     * @return Retorna o usuário localizado ou lança uma exceção ResourceNotFound caso não localizado
     */
    @GetMapping("protected/usuarios/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario usuario = this.usuarioService.findById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    /**
     * Retorna uma lista de usuário com paginação
     * @param pageable Objeto que contém informações e paginação
     * @return Retorna uma lista de usuários registrados
     */
    @GetMapping("admin/usuarios")
    public ResponseEntity<Page<Usuario>> findAll(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(this.usuarioService.findAll(pageable), HttpStatus.OK);
    }

    /**
     * Deleta um usuário pelo seu código identificador
     * Endpoint acessado somente por usuários com nível ADMIN
     * @param id Long - Código identificador do usuário
     * @return Retorna nulo caso houver sucesso ou lança a exceção ResourceNotFound caso não localizado
     */
    @DeleteMapping("admin/usuarios/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id) {
        Usuario usuario = this.usuarioService.findById(id);
        this.usuarioService.delete(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.NO_CONTENT);
    }
}
