package davi.brito.silva.SistemaConcessionaria.core.model;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteVenda(
        UUID id,
        String nomeCliente,
        String cpfCliente,
        String tefefoneCliente,
        VeiculoEntity veiculo,
        VendedorEntity vendedor,
        String tipoPagamento,
        LocalDateTime dataVenda) {
}
