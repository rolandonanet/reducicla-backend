package br.com.reducicla.service;

import br.com.reducicla.model.Comentario;
import br.com.reducicla.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Gustavo Miguel on 02/06/2021
 */

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @Transactional
    public Comentario save(Comentario comentario) {
        return this.comentarioRepository.save(comentario);
    }


    public Comentario findById(Long id) {
        return this.comentarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Comentario não encontrado"));
    }

    public void delete(Comentario comentario) {
        this.comentarioRepository.delete(comentario);
    }

    public Page<Comentario> findAll(Pageable pageable, Long postId) {
        if (postId != 0) {
            return this.comentarioRepository.findAllByPostId(pageable, postId);
        } else {
            return this.comentarioRepository.findAll(pageable);
        }
    }
}
