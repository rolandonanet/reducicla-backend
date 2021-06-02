package br.com.reducicla.service;

import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.Coletor;
import br.com.reducicla.repository.ColetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Copque on 02/06/2021
 */

@Service
public class ColetorService {

    private final ColetorRepository coletorRepository;

    @Autowired
    public ColetorService(ColetorRepository coletorRepository) {
        this.coletorRepository = coletorRepository;
    }

    public Coletor findById(Long id) {
        return this.coletorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coletor não encontrado"));
    }

    public Page<Coletor> findAll(Pageable pageable) {
        return this.coletorRepository.findAll(pageable);
    }

    public Long count() {
        return this.coletorRepository.count();
    }

}
