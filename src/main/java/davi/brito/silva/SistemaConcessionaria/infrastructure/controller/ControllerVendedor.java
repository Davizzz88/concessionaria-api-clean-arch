package davi.brito.silva.SistemaConcessionaria.infrastructure.controller;

import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.atualizar.AtualizarVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.buscarPorId.BuscarVendedorPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.criar.CriarVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.remover.RemoverVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.VendedorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/concessionaria")
@RequiredArgsConstructor
public class ControllerVendedor {

    private final CriarVendedorUseCase criarVendedorUseCase;
    private final AtualizarVendedorUseCase atualizarVendedorUseCase;
    private final RemoverVendedorUseCase removerVendedorUseCase;
    private final BuscarVendedorPorIdUseCase buscarVendedorPorIdUseCase;

    private final VendedorMapper vendedorMapper;

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

}
