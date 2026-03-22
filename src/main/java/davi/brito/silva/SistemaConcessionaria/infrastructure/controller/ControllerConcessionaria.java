package davi.brito.silva.SistemaConcessionaria.infrastructure.controller;

import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar.AtualizarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId.BuscarConcessionatiaPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.criar.CriarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.remover.RemoverConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.atualizar.AtualizarVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.buscarPorId.BuscarVeiculoPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.criar.CriarVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.remover.RemoverVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.buscarPorId.BuscarClienteVendaPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.criar.CriarClienteVendaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.atualizar.AtualizarVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.buscarPorId.BuscarVendedorPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.criar.CriarVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.remover.RemoverVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ClienteVendaMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ConcessionariaMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.VeiculoMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.VendedorMapper;
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
    private final BuscarConcessionatiaPorIdUseCase buscarConcessionariaPorIdUseCase;

    private final CriarVendedorUseCase criarVendedorUseCase;
    private final AtualizarVendedorUseCase atualizarVendedorUseCase;
    private final RemoverVendedorUseCase removerVendedorUseCase;
    private final BuscarVendedorPorIdUseCase buscarVendedorPorIdUseCase;

    private final CriarVeiculoUseCase criarVeiculoUseCase;
    private final AtualizarVeiculoUseCase atualizarVeiculoUseCase;
    private final RemoverVeiculoUseCase removerVeiculoUseCase;
    private final BuscarVeiculoPorIdUseCase buscarVeiculoPorIdUseCase;

    private final CriarClienteVendaUseCase criarClienteVendaUseCase;
    private final BuscarClienteVendaPorIdUseCase buscarClienteVendaPorIdUseCase;

    private final ConcessionariaMapper concessionariaMapper;
    private final VendedorMapper vendedorMapper;
    private final VeiculoMapper veiculoMapper;
    private final ClienteVendaMapper clienteVendaMapper;

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

    @PostMapping("/vendedor")
    public ResponseEntity<VendedorResponse> criarVendedor(@RequestBody VendedorRequest request) {
        var criada = criarVendedorUseCase.execute(vendedorMapper.toDomain(vendedorMapper.toRequest(request)));
        return ResponseEntity.ok(vendedorMapper.toResponse(criada));
    }

    @GetMapping("/vendedor/{id}")
    public ResponseEntity<VendedorResponse> buscarVendedorPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(vendedorMapper.toResponse(buscarVendedorPorIdUseCase.execute(id)));
    }

    @PutMapping("/vendedor/{id}")
    public ResponseEntity<VendedorResponse> atualizarVendedor(@PathVariable("id") UUID id,
                                                              @RequestBody VendedorRequest request) {
        var vendedor = vendedorMapper.toRequest(request);
        vendedor.setId(id);
        var atualizada = atualizarVendedorUseCase.execute(vendedorMapper.toDomain(vendedor));
        return ResponseEntity.ok(vendedorMapper.toResponse(atualizada));
    }

    @DeleteMapping("/vendedor/{id}")
    public ResponseEntity<Void> removerVendedor(@PathVariable("id") UUID id) {
        removerVendedorUseCase.execute(buscarVendedorPorIdUseCase.execute(id));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/veiculo")
    public ResponseEntity<VeiculoResponse> criarVeiculo(@RequestBody VeiculoRequest request) {
        var criada = criarVeiculoUseCase.execute(veiculoMapper.toDomain(veiculoMapper.toRequest(request)));
        return ResponseEntity.ok(veiculoMapper.toResponse(criada));
    }

    @GetMapping("/veiculo/{id}")
    public ResponseEntity<VeiculoResponse> buscarVeiculoPorId(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(veiculoMapper.toResponse(buscarVeiculoPorIdUseCase.execute(id)));
    }

    @PutMapping("/veiculo/{id}")
    public ResponseEntity<VeiculoResponse> atualizarVeiculo(@PathVariable("id") UUID id,
                                                            @RequestBody VeiculoRequest request) {
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

    @PostMapping("/venda/{id}")
    public ResponseEntity<ClienteVendaResponse> criarClienteVenda(@RequestBody ClienteVendaRequest request,
                                                                  @PathVariable("id") UUID idVeiculo) {
        var criada = criarClienteVendaUseCase.execute(clienteVendaMapper.
                toDomain(clienteVendaMapper.toRequest(request)), idVeiculo);
        return ResponseEntity.ok(clienteVendaMapper.toResponse(criada));
    }

    @GetMapping("/venda/{id}")
    public ResponseEntity<ClienteVendaResponse> buscarClienteVenda(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(clienteVendaMapper.toResponse(buscarClienteVendaPorIdUseCase.execute(id)));
    }
}
