package br.com.reducicla.service;

import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.Coleta;
import br.com.reducicla.repository.ColetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ColetaService {

    private final ColetaRepository coletaRepository;

    @Transactional
    public Coleta save(Coleta coleta) {
        return this.coletaRepository.save(coleta);
    }

    public Coleta findById(Long id) {
        return this.coletaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coleta não encontrada"));
    }

    public Page<Coleta> findAll(Pageable pageable) {
        return this.coletaRepository.findAll(pageable);
    }

    public void delete(Coleta coleta) {
        this.coletaRepository.delete(coleta);
    }
}
