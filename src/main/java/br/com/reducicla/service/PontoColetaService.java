package br.com.reducicla.service;

import br.com.reducicla.dto.response.PontoColetaResponseDTO;
import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.PontoColeta;
import br.com.reducicla.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Service
public class PontoColetaService {

    private final PontoColetaRepository pontoColetaRepository;

    @Autowired
    public PontoColetaService(PontoColetaRepository pontoColetaRepository) {
        this.pontoColetaRepository = pontoColetaRepository;
    }

    public PontoColeta save(PontoColeta pontoColeta) {
        return this.pontoColetaRepository.save(pontoColeta);
    }

    public void updateStatus(PontoColeta pontoColeta, Boolean status) {
        pontoColeta.setAprovado(status);
        this.pontoColetaRepository.save(pontoColeta);
    }

    public PontoColeta findById(Long id) {
        return this.pontoColetaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ponto de Coleta não encontrado"));
    }

    public Page<PontoColetaResponseDTO> findAll(Pageable pageable, Boolean aprovado) {
        if (aprovado != null) {
            Page<PontoColeta> pontoColetaPage = this.pontoColetaRepository.findAllByAprovado(pageable, aprovado);
            List<PontoColetaResponseDTO> pontoColetaResponseDTOList = pontoColetaPage.getContent().stream().map(PontoColetaResponseDTO::new).collect(Collectors.toList());
            return new PageImpl<>(pontoColetaResponseDTOList, pageable, pontoColetaPage.getTotalElements());
        } else {
            Page<PontoColeta> pontoColetaPage = this.pontoColetaRepository.findAll(pageable);
            List<PontoColetaResponseDTO> pontoColetaResponseDTOList = pontoColetaPage.getContent().stream().map(PontoColetaResponseDTO::new).collect(Collectors.toList());
            return new PageImpl<>(pontoColetaResponseDTOList, pageable, pontoColetaPage.getTotalElements());
        }
    }

    public void delete(PontoColeta pontoColeta) {
        this.pontoColetaRepository.delete(pontoColeta);
    }
}
