package br.com.reducicla.service;

import br.com.reducicla.dto.response.ColetaResponseDTO;
import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.Coleta;
import br.com.reducicla.repository.ColetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas Copque on 02/06/2021
 */

@Service
public class ColetaService {

    private final ColetaRepository coletaRepository;

    @Autowired
    public ColetaService(ColetaRepository coletaRepository) {
        this.coletaRepository = coletaRepository;
    }

    @Transactional
    public Coleta save(Coleta coleta) {
        return this.coletaRepository.save(coleta);
    }

    public Coleta findById(Long id) {
        return this.coletaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Coleta não encontrada"));
    }

    public Page<ColetaResponseDTO> findAll(Pageable pageable, Long colaboradorId, Long coletorId) {
        if (colaboradorId != null && coletorId == null) {
            Page<Coleta> coletaPage = this.coletaRepository.findAllByColaboradorId(pageable, colaboradorId);
            List<ColetaResponseDTO> coletaResponseDTOS = coletaPage.getContent().stream().map(ColetaResponseDTO::new).collect(Collectors.toList());
            return new PageImpl<>(coletaResponseDTOS, pageable, coletaPage.getTotalElements());
        } else if (colaboradorId == null && coletorId != null) {
            Page<Coleta> coletaPage = this.coletaRepository.findAllByColetorId(pageable, coletorId);
            List<ColetaResponseDTO> coletaResponseDTOS = coletaPage.getContent().stream().map(ColetaResponseDTO::new).collect(Collectors.toList());
            return new PageImpl<>(coletaResponseDTOS, pageable, coletaPage.getTotalElements());
        } else {
            Page<Coleta> coletaPage = this.coletaRepository.findAll(pageable);
            List<ColetaResponseDTO> coletaResponseDTOS = coletaPage.getContent().stream().map(ColetaResponseDTO::new).collect(Collectors.toList());
            return new PageImpl<>(coletaResponseDTOS, pageable, coletaPage.getTotalElements());
        }
    }

    public void delete(Coleta coleta) {
        this.coletaRepository.delete(coleta);
    }

    public Long count() {
        return this.coletaRepository.count();
    }
}
