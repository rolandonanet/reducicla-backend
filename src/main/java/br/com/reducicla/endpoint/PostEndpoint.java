package br.com.reducicla.endpoint;

import br.com.reducicla.model.Post;
import br.com.reducicla.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")

public class PostEndpoint {
    private final PostService postService;

    @Autowired
    public PostEndpoint(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("posts/save")
    public ResponseEntity<Post> save(@RequestBody Post post){
        this.postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id){
        Post post = this.postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("posts")
    public ResponseEntity<Page<Post>> findAll(@PageableDefault Pageable pageable){
        Page<Post> postPage = this.postService.findAll(pageable);
        return new ResponseEntity<>(postPage, HttpStatus.OK);
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Post post = this.postService.findById(id);
        this.postService.delete(post);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
