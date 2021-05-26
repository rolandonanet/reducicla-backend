package br.com.reducicla.endpoint;

import br.com.reducicla.model.Comentario;
import br.com.reducicla.service.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class ComentarioEndpoint {
    private final ComentarioService comentarioService;

    @PostMapping("protected/comentarios/save")
    public ResponseEntity<Comentario> save(@RequestBody Comentario comentario) {
        this.comentarioService.save(comentario);
        return new ResponseEntity<>(comentario, HttpStatus.CREATED);
    }

    @GetMapping("protected/comentarios/{id}")
    public ResponseEntity<Comentario> findById(@PathVariable Long id) {
        Comentario comentario = this.comentarioService.findById(id);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    @GetMapping("protected/comentarios")
    public ResponseEntity<Page<Comentario>> findAll(@PageableDefault Pageable pageable) {
        Page<Comentario> comentarioPage = this.comentarioService.findAll(pageable);
        return new ResponseEntity<>(comentarioPage, HttpStatus.OK);
    }

    @DeleteMapping("admin/comentarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Comentario comentario = this.comentarioService.findById(id);
        this.comentarioService.delete(comentario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
