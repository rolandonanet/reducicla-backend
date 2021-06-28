package br.com.reducicla.endpoint;

import br.com.reducicla.dto.request.PostRequestDTO;
import br.com.reducicla.model.Post;
import br.com.reducicla.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoint mapeado à requests de post
 */

@RestController
@RequestMapping("v1")
public class PostEndpoint {

    private final PostService postService;

    @Autowired
    public PostEndpoint(PostService postService) {
        this.postService = postService;
    }

    /**
     * Registra um post
     * @param postRequestDTO Objeto que irá fazer o binding do post
     * @return Retorna o post salvo
     */
    @PostMapping("admin/posts")
    public ResponseEntity<Post> save(@RequestBody PostRequestDTO postRequestDTO) {
        return new ResponseEntity<>(this.postService.save(new Post(postRequestDTO)), HttpStatus.CREATED);
    }

    /**
     * Busca um post pelo seu código identificador
     * @param id Long - Código identificador do post
     * @return Retorna o post localizado ou lança uma exceção ResourceNotFound caso não localizado
     */
    @GetMapping("protected/posts/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post post = this.postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    /**
     * Lista os posts com paginação
     * @param pageable Objeto que contém informações de paginação
     * @return Retorna uma lista de post com paginação
     */
    @GetMapping("posts")
    public ResponseEntity<Page<Post>> findAll(@PageableDefault Pageable pageable) {
        Page<Post> postPage = this.postService.findAll(pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    /**
     * Deleta um post pelo seu código identificador
     * Endpoint acessado somente por usuário com nível ADMIN
     * @param id Long - Código identificador do post
     * @return Retorna nulo caso houver sucesso ou lança a exceção ResourceNotFound caso não localizado
     */
    @DeleteMapping("admin/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Post post = this.postService.findById(id);
        this.postService.delete(post);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Contabiliza o número de posts registrador
     * Endpoint acessado somente por usuários com nível ADMIN
     * @return Retorna o número de posts registrados
     */
    @GetMapping("admin/posts/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(this.postService.count(), HttpStatus.OK);
    }
}
