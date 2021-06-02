package br.com.reducicla.service;

import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.Colaborador;
import br.com.reducicla.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Copque on 02/06/2021
 */

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    @Autowired
    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public Colaborador findById(Long id) {
        return this.colaboradorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Colaborador não encontrado"));
    }

    public Page<Colaborador> findAll(Pageable pageable, Boolean materiais) {
        if (materiais != null) {
            if (materiais) return this.colaboradorRepository.findAll(pageable);
            else return this.colaboradorRepository.findAllByMateriaisNotNull(pageable);
        } else return this.colaboradorRepository.findAll(pageable);
    }

    public Long count() {
        return this.colaboradorRepository.count();
    }
}
