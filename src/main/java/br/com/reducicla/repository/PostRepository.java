package br.com.reducicla.repository;

import br.com.reducicla.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gustavo Miguel on 19/05/2021
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
