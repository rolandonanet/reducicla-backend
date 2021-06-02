package br.com.reducicla.service;

import br.com.reducicla.dto.response.ChartResponseDTO;
import br.com.reducicla.enumerated.Meses;
import br.com.reducicla.enumerated.TipoMaterial;
import br.com.reducicla.model.Coleta;
import br.com.reducicla.model.Material;
import br.com.reducicla.repository.ColetaRepository;
import br.com.reducicla.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Lucas Copque on 01/06/2021
 */

@Service
public class ChartService {

    private final ColetaRepository coletaRepository;
    private final MaterialRepository materialRepository;

    @Autowired
    public ChartService(ColetaRepository coletaRepository, MaterialRepository materialRepository) {
        this.coletaRepository = coletaRepository;
        this.materialRepository = materialRepository;
    }

    public List<ChartResponseDTO> buildTimeLineChart(Integer ano) {
        List<ChartResponseDTO> chartResponseDTOS = new ArrayList<>();
        for (Meses mes : Meses.values()) {
            String label = mes.getDescricao();
            Calendar data = new GregorianCalendar(ano, mes.getId(), 1);
            Long value = this.coletaRepository.countByDataColetaBetween(
                    new GregorianCalendar(ano, mes.getId(), data.getActualMinimum(5)).getTime(),
                    new GregorianCalendar(ano, mes.getId(), data.getActualMaximum(5)).getTime()
            );
            chartResponseDTOS.add(new ChartResponseDTO(label, value));
        }
        return chartResponseDTOS;
    }

    public List<ChartResponseDTO> buildColumnChart(Date inicio, Date fim) {
        List<ChartResponseDTO> chartResponseDTOS = new ArrayList<>();
        List<Coleta> coletas = this.coletaRepository.findAllByDataColetaBetween(inicio, fim);
        Map<TipoMaterial, Integer> totalMateriais = new HashMap<>();
        for (Coleta coleta : coletas) {
            List<Material> materiais = coleta.getMateriais();
            materiais.forEach(material -> {
                if (material.getTipo() == TipoMaterial.PLASTICO) {
                    Integer total = totalMateriais.get(TipoMaterial.PLASTICO) != null ? totalMateriais.get(TipoMaterial.PLASTICO) + material.getQuantidade() : material.getQuantidade();
                    totalMateriais.put(TipoMaterial.PLASTICO, total);
                } else if (material.getTipo() == TipoMaterial.VIDRO) {
                    Integer total = totalMateriais.get(TipoMaterial.VIDRO) != null ? totalMateriais.get(TipoMaterial.VIDRO) + material.getQuantidade() : material.getQuantidade();
                    totalMateriais.put(TipoMaterial.VIDRO, total);
                } else if (material.getTipo() == TipoMaterial.METAL) {
                    Integer total = totalMateriais.get(TipoMaterial.METAL) != null ? totalMateriais.get(TipoMaterial.METAL) + material.getQuantidade() : material.getQuantidade();
                    totalMateriais.put(TipoMaterial.METAL, total);
                } else if (material.getTipo() == TipoMaterial.PAPEL) {
                    Integer total = totalMateriais.get(TipoMaterial.PAPEL) != null ? totalMateriais.get(TipoMaterial.PAPEL) + material.getQuantidade() : material.getQuantidade();
                    totalMateriais.put(TipoMaterial.PAPEL, total);
                }
            });
        }
        totalMateriais.forEach(((tipoMaterial, total) -> {
            chartResponseDTOS.add(new ChartResponseDTO(tipoMaterial.getLabel(), total.longValue()));
        }));
        return chartResponseDTOS;
    }
}
