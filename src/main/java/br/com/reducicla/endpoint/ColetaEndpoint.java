package br.com.reducicla.endpoint;

import br.com.reducicla.dto.response.ColetaResponseDTO;
import br.com.reducicla.model.Colaborador;
import br.com.reducicla.model.Coleta;
import br.com.reducicla.model.Coletor;
import br.com.reducicla.model.Material;
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

/**
 * Endpoint mapeado à requests de coleta
 */

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

    /**
     * Registra uma coleta
     * @param coletorId Long - Código identificador do colaborador associado à esta coleta
     * @param colaboradorId Long - Código identificador do coletor associado à esta coleta
     * @return Retorna a coleta salva
     */
    @PostMapping("protected/coletas")
    public ResponseEntity<ColetaResponseDTO> save(@RequestParam Long coletorId, @RequestParam Long colaboradorId) {
        Coletor coletor = (Coletor) this.usuarioService.findById(coletorId);
        Colaborador colaborador = (Colaborador) this.usuarioService.findById(colaboradorId);
        Coleta coleta = this.coletaService.save(new Coleta(coletor, colaborador));
        this.startColeta(coleta, colaborador);
        return new ResponseEntity<>(new ColetaResponseDTO(coleta), HttpStatus.CREATED);
    }

    /**
     * Busca uma coleta
     * @param id Long - Código identificador da coleta
     * @return Retorna a coleta localizada ou lança uma exceção ResourceNotFound caso não localizada
     */
    @GetMapping("protected/coletas/{id}")
    public ResponseEntity<ColetaResponseDTO> findById(@PathVariable Long id) {
        Coleta coleta = this.coletaService.findById(id);
        return new ResponseEntity<>(new ColetaResponseDTO(coleta), HttpStatus.OK);
    }

    /**
     * Retorna uma lista de coletas com paginação
     * @param pageable Objeto que contém informações de paginação
     * @param colaboradorId Parâmetro não obrigatório. Filtra as coletas por colaborador -> Long - Código identificador do colaborador
     * @param coletorId Parâmetro não obrigatório. Filtra as coletas por coletor -> Long - Código identificador do coletor
     * @return Retorna uma lista de coletas com paginação
     */
    @GetMapping("protected/coletas")
    public ResponseEntity<Page<ColetaResponseDTO>> findAll(@PageableDefault Pageable pageable, @RequestParam(required = false) Long colaboradorId, @RequestParam(required = false) Long coletorId) {
        return new ResponseEntity<>(this.coletaService.findAll(pageable, colaboradorId, coletorId), HttpStatus.OK);
    }

    /**
     * Deleta uma coleta.
     * Somente usuários com nível ADMIN conseguem acessar este endpoint.
     * @param id Long - Código identificador da coleta
     * @return Retorna nulo caso houver sucesso ou lança a exceção ResourceNotFound caso não localizado
     */
    @DeleteMapping("admin/coletas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Coleta coleta = this.coletaService.findById(id);
        this.coletaService.delete(coleta);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Contabiliza os registro de coleta
     * @return Retorna no número de coletas registradas
     */
    @GetMapping("admin/coletas/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(this.coletaService.count(), HttpStatus.OK);
    }


    /**
     * Método auxiliar para buildar os objetos associados a coleta
     * @param coleta Objeto que contém informações referente a coleta
     * @param colaborador Objeto que contém informações referente ao colaborador
     */
    private void startColeta(Coleta coleta, Colaborador colaborador) {
        List<Material> materiais = colaborador.getMateriais();
        materiais.forEach(material -> this.materialService.coletaMaterial(material, coleta));
        coleta.setMateriais(materiais);
    }
}
