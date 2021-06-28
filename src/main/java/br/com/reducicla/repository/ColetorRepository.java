package br.com.reducicla.repository;

import br.com.reducicla.model.Coletor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gustavo Miguel on 19/05/2021
 */

@Repository
public interface ColetorRepository extends JpaRepository<Coletor, Long> {
}
