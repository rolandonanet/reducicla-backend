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

/**
 * Endpoint mapeado à requests de charts
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
     * Endpoint que disponibiliza as informações para o gráfico de timeline da Dashboard
     * @param ano Inteiro - ano da consulta
     * @return Retorna uma lista de objetos com label e value para build do chart
     */
    @GetMapping("admin/charts/time-line")
    public ResponseEntity<List<ChartResponseDTO>> buildTimeLineChart(@RequestParam Integer ano) {
        List<ChartResponseDTO> chartResponseDTOS = this.chartService.buildTimeLineChart(ano);
        return new ResponseEntity<>(chartResponseDTOS, HttpStatus.OK);
    }

    /**
     * Endpoint que disponibiliza as informações para o gráfico de coluna da Dashboard
     * @param inicio Data - início da consulta
     * @param fim Data - fim da consulta
     * @return Retorna uma lista de objetos com label e value para build do chart
     */
    @GetMapping("admin/charts/column")
    public ResponseEntity<List<ChartResponseDTO>> buildColumnChart(@RequestParam Date inicio, Date fim) {
        List<ChartResponseDTO> chartResponseDTOS = this.chartService.buildColumnChart(inicio, fim);
        return new ResponseEntity<>(chartResponseDTOS, HttpStatus.OK);
    }
}
