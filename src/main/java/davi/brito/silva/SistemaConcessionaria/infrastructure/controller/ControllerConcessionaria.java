package davi.brito.silva.SistemaConcessionaria.infrastructure.controller;

import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar.AtualizarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId.BuscarConcessionariaPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.criar.CriarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.remover.RemoverConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ConcessionariaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/concessionaria")
@RequiredArgsConstructor
public class ControllerConcessionaria {

    private final CriarConcessionariaUseCase criarConcessionariaUseCase;
    private final AtualizarConcessionariaUseCase atualizarConcessionariaUseCase;
    private final RemoverConcessionariaUseCase removerConcessionariaUseCase;
    private final BuscarConcessionariaPorIdUseCase buscarConcessionariaPorIdUseCase;

    private final ConcessionariaMapper concessionariaMapper;

    @PostMapping
    public ResponseEntity<ConcessionariaResponse> criarConcessionaria(@RequestBody ConcessionariaRequest request) {
        var criada = criarConcessionariaUseCase.execute(concessionariaMapper.toDomain(concessionariaMapper.toRequest(request)));
        return ResponseEntity.ok(concessionariaMapper.toResponse(criada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcessionariaResponse> buscarConcessionariaPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(concessionariaMapper.toResponse(buscarConcessionariaPorIdUseCase.execute(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConcessionariaResponse> atualizarConcessionaria(@PathVariable("id") UUID id,
                                                                          @RequestBody ConcessionariaRequest request) {
        var concessionaria = concessionariaMapper.toRequest(request);
        concessionaria.setId(id);
        var atualizada = atualizarConcessionariaUseCase.execute(concessionariaMapper.toDomain(concessionaria));
        return ResponseEntity.ok(concessionariaMapper.toResponse(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerConcessionaria(@PathVariable("id") UUID id) {
        removerConcessionariaUseCase.execute(buscarConcessionariaPorIdUseCase.execute(id));
        return ResponseEntity.noContent().build();
    }
}
