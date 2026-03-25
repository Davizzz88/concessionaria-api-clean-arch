package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteVendaResponse(
        UUID id,
        String nomeCliente,
        String telefoneCliente,
        UUID idVeiculo,
        UUID idVendedor,
        LocalDateTime dataVenda) {
}
