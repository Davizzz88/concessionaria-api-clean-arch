package davi.brito.silva.SistemaConcessionaria.core.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteVenda(
        UUID id,
        String nomeCliente,
        String cpfCliente,
        String tefefoneCliente,
        Veiculo veiculo,
        Vendedor vendedor,
        String tipoPagamento,
        LocalDateTime dataVenda) {
}
