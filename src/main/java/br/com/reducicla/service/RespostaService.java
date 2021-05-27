package br.com.reducicla.service;

import br.com.reducicla.exception.ResourceNotFoundException;
import br.com.reducicla.model.Resposta;
import br.com.reducicla.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lucas Copque on 26/05/2021
 */

@Service
public class RespostaService {

    private final RespostaRepository respostaRepository;

    @Autowired
    public RespostaService(RespostaRepository respostaRepository) {
        this.respostaRepository = respostaRepository;
    }

    public Resposta save(Resposta resposta){
        return this.respostaRepository.save(resposta);
    }

    public Resposta findById(Long id){
        return this.respostaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resposta não encontrada"));
    }

    public void delete(Resposta resposta){
        this.respostaRepository.delete(resposta);
    }
}
