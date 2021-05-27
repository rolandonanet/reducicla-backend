package br.com.reducicla.repository;

import br.com.reducicla.model.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {
}