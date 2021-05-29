package br.com.reducicla.endpoint;

import br.com.reducicla.dto.request.MaterialRequestDTO;
import br.com.reducicla.model.Colaborador;
import br.com.reducicla.model.Material;
import br.com.reducicla.service.MaterialService;
import br.com.reducicla.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("protected/materiais")
    public ResponseEntity<Material> save(@RequestBody MaterialRequestDTO materialRequestDTO, @RequestParam Long colaboradorId) {
        Colaborador colaborador = (Colaborador) this.usuarioService.findById(colaboradorId);
        Material material = new Material(materialRequestDTO, colaborador);
        return new ResponseEntity<>(this.materialService.save(material), HttpStatus.CREATED);
    }

    @GetMapping("protected/materiais/{id}")
    public ResponseEntity<Material> findById(@PathVariable Long id) {
        Material material = this.materialService.findById(id);
        return new ResponseEntity<>(material, HttpStatus.OK);
    }

    @DeleteMapping("admin/materiais/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Material material = this.materialService.findById(id);
        this.materialService.delete(material);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
