package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorResponse;

public record ConcessionariaResponse(
        UUID id,
        String nome,
        String cnpj,
        String estado,
        String cidade,
        String telefone,
        List<VendedorResponse> vendedor,
        List<VeiculoResponse> veiculo,
        LocalDateTime ultimaAtualizacao) {
}
