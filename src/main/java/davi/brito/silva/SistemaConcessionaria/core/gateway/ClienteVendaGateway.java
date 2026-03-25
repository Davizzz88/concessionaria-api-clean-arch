package davi.brito.silva.SistemaConcessionaria.core.gateway;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;

import java.util.UUID;

public interface ClienteVendaGateway {

    ClienteVenda criarClienteVenda(ClienteVenda clienteVenda, UUID idVeiculo);

    ClienteVenda buscarVendaPorId(UUID id);

}
