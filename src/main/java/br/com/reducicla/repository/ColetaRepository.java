package br.com.reducicla.repository;

import br.com.reducicla.model.Coleta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository

public interface ColetaRepository extends JpaRepository<Coleta, Long> {

    List<Coleta> findAllByDataColetaBetween(Date inicio, Date fim);

    Page<Coleta> findAllByColaboradorId(Pageable pageable, Long colaboradorId);

    Page<Coleta> findAllByColetorId(Pageable pageable, Long coletorId);

    Long countByDataColetaBetween(Date inicio, Date fim);
}
