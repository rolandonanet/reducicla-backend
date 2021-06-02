package br.com.reducicla.service;

import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.Post;
import br.com.reducicla.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post save(Post post) {
        return this.postRepository.save(post);
    }

    public Post findById(Long id) {
        return this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
    }

    public Page<Post> findAll(Pageable pageable) {
        return this.postRepository.findAll(pageable);
    }

    public void delete(Post post) {
        this.postRepository.delete(post);
    }

    public Long count(){
        return this.postRepository.count();
    }
}
