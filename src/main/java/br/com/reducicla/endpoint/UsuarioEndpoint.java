package br.com.reducicla.endpoint;

import br.com.reducicla.dto.request.UsuarioRequestDTO;
import br.com.reducicla.model.Usuario;
import br.com.reducicla.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author User on 26/05/2021
 */

@RestController
@RequestMapping("v1")
public class UsuarioEndpoint {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioEndpoint(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("usuarios/save")
    public ResponseEntity<?> save(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return new ResponseEntity<>(this.usuarioService.save(usuarioRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("protected/usuarios/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        Usuario usuario = this.usuarioService.findById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("protected/usuarios")
    public ResponseEntity<Page<Usuario>> findAll(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(this.usuarioService.findAll(pageable), HttpStatus.OK);
    }

    @DeleteMapping("admin/usuarios/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id){
        Usuario usuario = this.usuarioService.findById(id);
        this.usuarioService.delete(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.NO_CONTENT);
    }
}
