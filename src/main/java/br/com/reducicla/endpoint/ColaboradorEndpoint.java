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

@RestController
@RequestMapping("v1")
public class ColaboradorEndpoint {

    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorEndpoint(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("protected/colaboradores/{id}")
    public ResponseEntity<Colaborador> findById(@PathVariable Long id) {
        Colaborador colaborador = this.colaboradorService.findById(id);
        return new ResponseEntity<>(colaborador, HttpStatus.OK);
    }

    @GetMapping("protected/colaboradores")
    public ResponseEntity<Page<Colaborador>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) Boolean materiais) {
        return new ResponseEntity<>(this.colaboradorService.findAll(pageable, materiais), HttpStatus.OK);
    }

    @GetMapping("admin/colaboradores/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(this.colaboradorService.count(), HttpStatus.OK);
    }
}
