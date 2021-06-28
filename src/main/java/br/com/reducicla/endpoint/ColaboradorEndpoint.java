package br.com.reducicla.endpoint;

import br.com.reducicla.model.Colaborador;
import br.com.reducicla.service.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucas Copque on 02/06/2021
 */

/**
 * Endpoint mapeado à requests de colaborador
 */
@RestController
@RequestMapping("v1")
public class ColaboradorEndpoint {

    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorEndpoint(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    /**
     * Busca um colaborador pelo seu código identificador
     * @param id Long - código identificador do colaborador
     * @return Retorna o colaborador localizado ou lança a exceção ResourceNotFound caso não localizado
     */
    @GetMapping("protected/colaboradores/{id}")
    public ResponseEntity<Colaborador> findById(@PathVariable Long id) {
        Colaborador colaborador = this.colaboradorService.findById(id);
        return new ResponseEntity<>(colaborador, HttpStatus.OK);
    }

    /**
     * Lista todos os colaboradores dentro de um objeto de paginação
     * @param pageable Objeto que possui informações para paginação
     * @param materiais Parâmetro não obrigatório. Caso true ele lista os colaboradores que possui materiais disponíveis para coleta e
     *                  caso false ele lista todos os colaboradores
     * @return Retorna a lista de colaboradores com paginação
     */
    @GetMapping("protected/colaboradores")
    public ResponseEntity<Page<Colaborador>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) Boolean materiais) {
        return new ResponseEntity<>(this.colaboradorService.findAll(pageable, materiais), HttpStatus.OK);
    }

    /**
     * Contabiliza o número de registros para colaboradores
     * @return Retorna o número de registros de colaboradores encontrado
     */
    @GetMapping("admin/colaboradores/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(this.colaboradorService.count(), HttpStatus.OK);
    }
}
