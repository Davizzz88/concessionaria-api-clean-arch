package davi.brito.silva.SistemaConcessionaria.infrastructure.controller;

import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.atualizar.AtualizarVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.buscarPorId.BuscarVeiculoPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.criar.CriarVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.remover.RemoverVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.VeiculoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/concessionaria")
@RequiredArgsConstructor
public class ControllerVeiculo {

    private final CriarVeiculoUseCase criarVeiculoUseCase;
    private final AtualizarVeiculoUseCase atualizarVeiculoUseCase;
    private final RemoverVeiculoUseCase removerVeiculoUseCase;
    private final BuscarVeiculoPorIdUseCase buscarVeiculoPorIdUseCase;

    private final VeiculoMapper veiculoMapper;

    @PostMapping("/veiculo")
    public ResponseEntity<VeiculoResponse> criarVeiculo(@Valid @RequestBody VeiculoRequest request) {
        var criada = criarVeiculoUseCase.execute(veiculoMapper.toDomain(veiculoMapper.toRequest(request)));
        return ResponseEntity.ok(veiculoMapper.toResponse(criada));
    }

    @GetMapping("/veiculo/{id}")
    public ResponseEntity<VeiculoResponse> buscarVeiculoPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(veiculoMapper.toResponse(buscarVeiculoPorIdUseCase.execute(id)));
    }

    @PutMapping("/veiculo/{id}")
    public ResponseEntity<VeiculoResponse> atualizarVeiculo(@PathVariable("id") UUID id,
                                                            @Valid @RequestBody VeiculoRequest request) {
        var veiculo = veiculoMapper.toRequest(request);
        veiculo.setId(id);
        var atualizada = atualizarVeiculoUseCase.execute(veiculoMapper.toDomain(veiculo));
        return ResponseEntity.ok(veiculoMapper.toResponse(atualizada));
    }

    @DeleteMapping("/veiculo/{id}")
    public ResponseEntity<Void> removerVeiculo(@PathVariable("id") UUID id) {
        removerVeiculoUseCase.execute(buscarVeiculoPorIdUseCase.execute(id));
        return ResponseEntity.noContent().build();
    }

}
