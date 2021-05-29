package br.com.reducicla.repository;

import br.com.reducicla.model.Comentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    Page<Comentario> findAllByPostId(Pageable pageable, Long postId);
}
