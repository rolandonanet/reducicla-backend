package br.com.reducicla.endpoint;

import br.com.reducicla.dto.request.ComentarioRequestDTO;
import br.com.reducicla.model.Comentario;
import br.com.reducicla.model.Post;
import br.com.reducicla.service.ComentarioService;
import br.com.reducicla.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint mapeado à requests de comentário
 */

@RestController
@RequestMapping("v1")
public class ComentarioEndpoint {

    private final ComentarioService comentarioService;
    private final PostService postService;

    @Autowired
    public ComentarioEndpoint(ComentarioService comentarioService, PostService postService) {
        this.comentarioService = comentarioService;
        this.postService = postService;
    }

    /**
     * Registra um comentário de um post
     * @param comentarioRequestDTO Objeto que irá fazer o binding das informações referente ao comentário
     * @param postId Long - Código identificador do post que o comentário está atrelado
     * @return Retorna o comentários registrado
     */
    @PostMapping("protected/comentarios")
    public ResponseEntity<Comentario> save(@RequestBody ComentarioRequestDTO comentarioRequestDTO, @RequestParam Long postId) {
        Post post = this.postService.findById(postId);
        return new ResponseEntity<>(this.comentarioService.save(new Comentario(comentarioRequestDTO, post)), HttpStatus.CREATED);
    }

    /**
     * Busca um comentário pelo seu código identificador
     * @param id Long - Código identificador do comentários
     * @return Retorna o comentário localizado ou lança uma exceção ResourceNotFound caso não localizado
     */
    @GetMapping("protected/comentarios/{id}")
    public ResponseEntity<Comentario> findById(@PathVariable Long id) {
        Comentario comentario = this.comentarioService.findById(id);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    /**
     * Retorna a lista de comentários com paginação
     * @param pageable Objeto que contém informações de paginação
     * @param postId Parâmetro Obrigatório => Long - Código identificador do post que deseja visualizar os comentários
     * @return Retorna uma lista de comentários com paginação
     */
    @GetMapping("protected/comentarios")
    public ResponseEntity<Page<Comentario>> findAll(@PageableDefault Pageable pageable, @RequestParam Long postId) {
        Page<Comentario> comentarioPage = this.comentarioService.findAll(pageable, postId);
        return new ResponseEntity<>(comentarioPage, HttpStatus.OK);
    }

    /**
     * Deleta um comentário pelo seu código identificador
     * @param id Long - Código identificador do comentário
     * @return Retorna nulo caso houver sucesso ou lança a exceção ResourceNotFound caso não localizado
     */
    @DeleteMapping("protected/comentarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Comentario comentario = this.comentarioService.findById(id);
        this.comentarioService.delete(comentario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
