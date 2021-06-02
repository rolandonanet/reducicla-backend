package br.com.reducicla.endpoint;

import br.com.reducicla.dto.response.ColetaResponseDTO;
import br.com.reducicla.model.*;
import br.com.reducicla.service.ColetaService;
import br.com.reducicla.service.MaterialService;
import br.com.reducicla.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
public class ColetaEndpoint {


    private final ColetaService coletaService;
    private final UsuarioService usuarioService;
    private final MaterialService materialService;

    @Autowired
    public ColetaEndpoint(ColetaService coletaService, UsuarioService usuarioService, MaterialService materialService) {
        this.coletaService = coletaService;
        this.usuarioService = usuarioService;
        this.materialService = materialService;
    }


    @PostMapping("protected/coletas")
    public ResponseEntity<ColetaResponseDTO> save(@RequestParam Long coletorId, @RequestParam Long colaboradorId) {
        Coletor coletor = (Coletor) this.usuarioService.findById(coletorId);
        Colaborador colaborador = (Colaborador) this.usuarioService.findById(colaboradorId);
        Coleta coleta = this.coletaService.save(new Coleta(coletor, colaborador));
        this.startColeta(coleta, colaborador);
        return new ResponseEntity<>(new ColetaResponseDTO(coleta), HttpStatus.CREATED);
    }

    @GetMapping("protected/coletas/{id}")
    public ResponseEntity<ColetaResponseDTO> findById(@PathVariable Long id) {
        Coleta coleta = this.coletaService.findById(id);
        return new ResponseEntity<>(new ColetaResponseDTO(coleta), HttpStatus.OK);
    }

    @GetMapping("protected/coletas")
    public ResponseEntity<Page<ColetaResponseDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) Long colaboradorId, @RequestParam(required = false) Long coletorId) {
        return new ResponseEntity<>(this.coletaService.findAll(pageable, colaboradorId, coletorId), HttpStatus.OK);
    }

    @DeleteMapping("admin/coletas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Coleta coleta = this.coletaService.findById(id);
        this.coletaService.delete(coleta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("admin/coletas/count")
    public ResponseEntity<Long> count(){
        return new ResponseEntity<>(this.coletaService.count(), HttpStatus.OK);
    }


    private void startColeta(Coleta coleta, Colaborador colaborador) {
        List<Material> materiais = colaborador.getMateriais();
        materiais.forEach(material -> this.materialService.coletaMaterial(material, coleta));
        coleta.setMateriais(materiais);
    }
}
