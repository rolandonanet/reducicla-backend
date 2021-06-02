package br.com.reducicla.endpoint;

import br.com.reducicla.model.Coletor;
import br.com.reducicla.service.ColetorService;
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
public class ColetorEndpoint {

    private final ColetorService coletorService;

    @Autowired
    public ColetorEndpoint(ColetorService coletorService) {
        this.coletorService = coletorService;
    }

    @GetMapping("protected/coletores/{id}")
    public ResponseEntity<Coletor> findById(@PathVariable Long id){
        Coletor coletor = this.coletorService.findById(id);
        return new ResponseEntity<>(coletor, HttpStatus.OK);
    }

    @GetMapping("protected/coletores")
    public ResponseEntity<Page<Coletor>> findAll(@PageableDefault Pageable pageable){
        return new ResponseEntity<>(this.coletorService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("admin/coletores/count")
    public ResponseEntity<Long> count(){
        return new ResponseEntity<>(this.coletorService.count(), HttpStatus.OK);
    }
}
