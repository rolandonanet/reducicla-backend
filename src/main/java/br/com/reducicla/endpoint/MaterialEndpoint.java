package br.com.reducicla.endpoint;

import br.com.reducicla.model.Material;
import br.com.reducicla.service.MaterialService;
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
@RequiredArgsConstructor

public class MaterialEndpoint {
    private final MaterialService materialService;

    @PostMapping("protected/material/save")
    public ResponseEntity<Material> save(@RequestBody Material material) {
        this.materialService.save(material);
        return new ResponseEntity<>(material, HttpStatus.CREATED);
    }

    @GetMapping("protected/material/{id}")
    public ResponseEntity<Material> findById(@PathVariable Long id) {
        Material material = this.materialService.findById(id);
        return new ResponseEntity<>(material, HttpStatus.OK);
    }

    @GetMapping("protected/material")
    public ResponseEntity<Page<Material>> findAll(@PageableDefault Pageable pageable) {
        Page<Material> materialPage = this.materialService.findAll(pageable);
        return new ResponseEntity<>(materialPage, HttpStatus.OK);
    }

    @DeleteMapping("admin/material/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Material material = this.materialService.findById(id);
        this.materialService.delete(material);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
