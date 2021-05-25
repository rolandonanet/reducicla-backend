package br.com.reducicla.repository;

import br.com.reducicla.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
