package davi.brito.silva.SistemaConcessionaria.infrastructure.controller;

import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar.AtualizarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId.BuscarConcessionariaPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.criar.CriarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.remover.RemoverConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaAtualizarRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ConcessionariaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<ConcessionariaResponse> criarConcessionaria(@Valid @RequestBody ConcessionariaRequest request) {
        var domain = concessionariaMapper.toDomain(concessionariaMapper.toRequest(request));
        domain = new Concessionaria(
                domain.id(),
                domain.nome(),
                domain.cnpj(),
                domain.estado(),
                domain.cidade(),
                domain.telefone(),
                passwordEncoder.encode(domain.senha()),
                domain.vendedor(),
                domain.veiculo(),
                LocalDateTime.now()
        );
        var criada = criarConcessionariaUseCase.execute(domain);
        return ResponseEntity.ok(concessionariaMapper.toResponse(criada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcessionariaResponse> buscarConcessionariaPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(concessionariaMapper.toResponse(buscarConcessionariaPorIdUseCase.execute(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConcessionariaResponse> atualizarConcessionaria(@PathVariable("id") UUID id,
                                                                          @Valid @RequestBody ConcessionariaAtualizarRequest request) {
        var concessionaria = concessionariaMapper.toAtualizarRequest(request);
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
