package br.com.reducicla.endpoint;

import br.com.reducicla.dto.request.MaterialRequestDTO;
import br.com.reducicla.model.Colaborador;
import br.com.reducicla.model.Coletor;
import br.com.reducicla.model.Material;
import br.com.reducicla.service.MaterialService;
import br.com.reducicla.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class MaterialEndpoint {

    private final MaterialService materialService;
    private final UsuarioService usuarioService;

    @Autowired
    public MaterialEndpoint(MaterialService materialService, UsuarioService usuarioService) {
        this.materialService = materialService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("protected/materiais/save")
    public ResponseEntity<Material> save(@RequestBody MaterialRequestDTO materialRequestDTO, @RequestParam Long colaboradorId) {
        Colaborador colaborador = (Colaborador) this.usuarioService.findById(colaboradorId);
        return new ResponseEntity<>(this.materialService.save(new Material(materialRequestDTO, colaborador)), HttpStatus.CREATED);
    }

    @GetMapping("protected/materiais/{id}")
    public ResponseEntity<Material> findById(@PathVariable Long id) {
        Material material = this.materialService.findById(id);
        return new ResponseEntity<>(material, HttpStatus.OK);
    }

    @GetMapping("protected/materiais")
    public ResponseEntity<Page<Material>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false, defaultValue = "0") Long coletorId) {
        Page<Material> materialPage = this.materialService.findAll(pageable, coletorId);
        return new ResponseEntity<>(materialPage, HttpStatus.OK);
    }

    @DeleteMapping("admin/materiais/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Material material = this.materialService.findById(id);
        this.materialService.delete(material);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
