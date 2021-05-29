package br.com.reducicla.endpoint;

import br.com.reducicla.dto.request.PontoColetaRequestDTO;
import br.com.reducicla.dto.response.PontoColetaResponseDTO;
import br.com.reducicla.model.Colaborador;
import br.com.reducicla.model.PontoColeta;
import br.com.reducicla.service.PontoColetaService;
import br.com.reducicla.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lucas Copque on 26/05/2021
 */

@RestController
@RequestMapping("v1")
public class PontoColetaEndpoint {

    private final PontoColetaService pontoColetaService;
    private final UsuarioService usuarioService;

    @Autowired
    public PontoColetaEndpoint(PontoColetaService pontoColetaService, UsuarioService usuarioService) {
        this.pontoColetaService = pontoColetaService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("protected/ponto-coleta")
    public ResponseEntity<PontoColetaResponseDTO> save(@RequestBody PontoColetaRequestDTO pontoColetaRequestDTO, @RequestParam Long colaboradorId) {
        Colaborador colaborador = (Colaborador) this.usuarioService.findById(colaboradorId);
        PontoColeta pontoColeta = this.pontoColetaService.save(new PontoColeta(pontoColetaRequestDTO, colaborador));
        return new ResponseEntity<>(new PontoColetaResponseDTO(pontoColeta), HttpStatus.CREATED);
    }

    @PutMapping("admin/ponto-coleta/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam Boolean status) {
        PontoColeta pontoColeta = this.pontoColetaService.findById(id);
        this.pontoColetaService.updateStatus(pontoColeta, status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("protected/ponto-coleta/{id}")
    public ResponseEntity<PontoColetaResponseDTO> findById(@PathVariable Long id) {
        PontoColeta pontoColeta = this.pontoColetaService.findById(id);
        return new ResponseEntity<>(new PontoColetaResponseDTO(pontoColeta), HttpStatus.OK);
    }

    @GetMapping("protected/ponto-coleta")
    public ResponseEntity<Page<PontoColetaResponseDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) Boolean aprovado) {
        return new ResponseEntity<>(this.pontoColetaService.findAll(pageable, aprovado), HttpStatus.OK);
    }

    @DeleteMapping("admin/ponto-coleta/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        PontoColeta pontoColeta = this.pontoColetaService.findById(id);
        this.pontoColetaService.delete(pontoColeta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
