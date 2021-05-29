package br.com.reducicla.endpoint;

import br.com.reducicla.dto.request.PostRequestDTO;
import br.com.reducicla.model.Post;
import br.com.reducicla.service.PostService;
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
public class PostEndpoint {

    private final PostService postService;

    @PostMapping("admin/posts")
    public ResponseEntity<Post> save(@RequestBody PostRequestDTO postRequestDTO) {
        return new ResponseEntity<>(this.postService.save(new Post(postRequestDTO)), HttpStatus.CREATED);
    }

    @GetMapping("protected/posts/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Post post = this.postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("protected/posts")
    public ResponseEntity<Page<Post>> findAll(@PageableDefault Pageable pageable) {
        Page<Post> postPage = this.postService.findAll(pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    @DeleteMapping("admin/posts/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Post post = this.postService.findById(id);
        this.postService.delete(post);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
