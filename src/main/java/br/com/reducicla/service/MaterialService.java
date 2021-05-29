package br.com.reducicla.service;

import br.com.reducicla.model.Coleta;
import br.com.reducicla.model.Material;
import br.com.reducicla.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service

public class MaterialService {
    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Transactional
    public Material save(Material material) {
        return this.materialRepository.save(material);
    }

    @Transactional
    public void coletaMaterial(Material material, Coleta coleta) {
        material.setColeta(coleta);
        material.setColaborador(null);
        this.save(material);
    }

    public Material findById(Long id) {
        return this.materialRepository.findById(id).orElseThrow(() -> new RuntimeException("Material não encontrado"));
    }

    public void delete(Material material) {
        this.materialRepository.delete(material);
    }
}
