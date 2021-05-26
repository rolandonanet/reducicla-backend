package br.com.reducicla.endpoint;

import br.com.reducicla.model.Coleta;
import br.com.reducicla.service.ColetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class ColetaEndpoint {
    private final ColetaService coletaService;

    @PostMapping("protected/coletas/save")
    public ResponseEntity<Coleta> save(@RequestBody Coleta coleta) {
        this.coletaService.save(coleta);
        return new ResponseEntity<>(coleta, HttpStatus.CREATED);
    }

    @GetMapping("protected/coletas/{id}")
    public ResponseEntity<Coleta> findById(@PathVariable Long id) {
        Coleta coleta = this.coletaService.findById(id);
        return new ResponseEntity<>(coleta, HttpStatus.OK);
    }

    @GetMapping("protected/coletas")
    public ResponseEntity<Page<Coleta>> findAll(@PageableDefault Pageable pageable) {
        Page<Coleta> coletaPage = this.coletaService.findAll(pageable);
        return new ResponseEntity<>(coletaPage, HttpStatus.OK);
    }

    @DeleteMapping("admin/coletas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Coleta coleta = this.coletaService.findById(id);
        this.coletaService.delete(coleta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
