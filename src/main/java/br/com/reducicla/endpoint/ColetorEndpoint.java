package br.com.reducicla.endpoint;

import br.com.reducicla.model.Coletor;
import br.com.reducicla.service.ColetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lucas Copque on 02/06/2021
 */

/**
 * Endpoint mapeado à requests de coletor
 */

@RestController
@RequestMapping("v1")
public class ColetorEndpoint {

    private final ColetorService coletorService;

    @Autowired
    public ColetorEndpoint(ColetorService coletorService) {
        this.coletorService = coletorService;
    }

    /**
     * Busca um coletor pelo seu código identificador
     * @param id Long - Código identificador do coletor
     * @return Retorna o coletor localizado ou lança a exceção ResourceNotFound caso não localizado
     */
    @GetMapping("protected/coletores/{id}")
    public ResponseEntity<Coletor> findById(@PathVariable Long id) {
        Coletor coletor = this.coletorService.findById(id);
        return new ResponseEntity<>(coletor, HttpStatus.OK);
    }

    /**
     * Lista os coletores registrados com paginação
     * @param pageable Objeto que contém informações de paginação
     * @return Retorna uma lista de coletores com paginação
     */
    @GetMapping("protected/coletores")
    public ResponseEntity<Page<Coletor>> findAll(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(this.coletorService.findAll(pageable), HttpStatus.OK);
    }

    /**
     * Contabiliza os registro de coletores
     * Endpoint acessado somente por usuários com nível ADMIN
     * @return Retorna o número de coletores registrados
     */
    @GetMapping("admin/coletores/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(this.coletorService.count(), HttpStatus.OK);
    }
}
