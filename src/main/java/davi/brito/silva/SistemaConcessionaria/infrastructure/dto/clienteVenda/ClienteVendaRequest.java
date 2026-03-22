package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;

public record ClienteVendaRequest(
        String nomeCliente,
        String cpfCliente,
        String tefefoneCliente,
        VeiculoEntity veiculo,
        VendedorEntity vendedor,
        String tipoPagamento) {
}
