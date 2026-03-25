package davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.criar;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ClienteVendaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;

import java.util.UUID;

public class CriarClienteVendaUseCaseImpl implements CriarClienteVendaUseCase{

    private final ClienteVendaGateway gateway;

    public CriarClienteVendaUseCaseImpl(ClienteVendaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public ClienteVenda execute(ClienteVenda clienteVenda, UUID idVeiculo) {
        return gateway.criarClienteVenda(clienteVenda, idVeiculo);
    }
}
