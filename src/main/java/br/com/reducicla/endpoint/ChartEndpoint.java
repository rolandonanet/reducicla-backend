package br.com.reducicla.endpoint;

import br.com.reducicla.dto.response.ChartResponseDTO;
import br.com.reducicla.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Lucas Copque on 01/06/2021
 */

@RestController
@RequestMapping("v1")
public class ChartEndpoint {

    private final ChartService chartService;

    @Autowired
    public ChartEndpoint(ChartService chartService) {
        this.chartService = chartService;
    }

    /**
     * Retorna os dados para o gráfico xpt
     *
     * @param ano
     * @return
     */
    @GetMapping("admin/charts/time-line")
    public ResponseEntity<List<ChartResponseDTO>> buildTimeLineChart(@RequestParam Integer ano) {
        List<ChartResponseDTO> chartResponseDTOS = this.chartService.buildTimeLineChart(ano);
        return new ResponseEntity<>(chartResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("admin/charts/column")
    public ResponseEntity<List<ChartResponseDTO>> buildColumnChart(@RequestParam Date inicio, Date fim) {
        List<ChartResponseDTO> chartResponseDTOS = this.chartService.buildColumnChart(inicio, fim);
        return new ResponseEntity<>(chartResponseDTOS, HttpStatus.OK);
    }
}
