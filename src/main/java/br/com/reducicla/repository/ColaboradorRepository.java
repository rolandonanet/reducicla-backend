package br.com.reducicla.repository;

import br.com.reducicla.model.Colaborador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author User on 19/05/2021
 */

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

    Page<Colaborador> findAllByMateriaisNotNull(Pageable pageable);
}
