package br.com.reducicla.repository;

import br.com.reducicla.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gustavo Miguel on 19/05/2021
 */

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    Page<Comentario> findAllByPostId(Pageable pageable, Long postId);
}
