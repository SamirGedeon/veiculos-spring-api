package br.com.estudo.veiculosapi;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/veiculos", produces="application/json")
public class VeiculoController {
    private final VeiculoModel model;
    public VeiculoController(VeiculoModel model) { this.model = model; }
    @PostMapping(consumes="application/json")
    public ResponseEntity<VeiculoResponse> criar(@Valid @RequestBody VeiculoRequest request) {
        Veiculo criado = model.criar(VeiculoMapper.toEntity(request, null));
        return ResponseEntity.created(URI.create("/veiculos/"+criado.getId())).body(VeiculoMapper.toResponse(criado));
    }
    @GetMapping
    public List<VeiculoResponse> listar(@RequestParam(name="marca", required=false) String marca,
            @RequestParam(name="placa", required=false) String placa) { return model.listar(marca, placa).stream().map(VeiculoMapper::toResponse).toList(); }
    @GetMapping("/{id}")
    public VeiculoResponse buscar(@PathVariable("id") Long id) { return VeiculoMapper.toResponse(model.buscar(id).orElseThrow(() -> new RecursoNaoEncontradoException(id))); }
    @PutMapping(value="/{id}", consumes="application/json")
    public VeiculoResponse atualizar(@PathVariable("id") Long id, @Valid @RequestBody VeiculoRequest request) {
        return VeiculoMapper.toResponse(model.atualizar(id, VeiculoMapper.toEntity(request,id)).orElseThrow(() -> new RecursoNaoEncontradoException(id)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        if (!model.excluir(id)) throw new RecursoNaoEncontradoException(id);
        return ResponseEntity.noContent().build();
    }
    
}
