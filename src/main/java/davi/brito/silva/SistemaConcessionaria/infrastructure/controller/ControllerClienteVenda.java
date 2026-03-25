package davi.brito.silva.SistemaConcessionaria.infrastructure.controller;

import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.buscarPorId.BuscarClienteVendaPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.criar.CriarClienteVendaUseCase;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ClienteVendaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/concessionaria")
@RequiredArgsConstructor
public class ControllerClienteVenda {

    private final CriarClienteVendaUseCase criarClienteVendaUseCase;
    private final BuscarClienteVendaPorIdUseCase buscarClienteVendaPorIdUseCase;

    private final ClienteVendaMapper clienteVendaMapper;


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
