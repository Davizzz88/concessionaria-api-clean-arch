package davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.ClienteVendaNaoEncontradaException;
import davi.brito.silva.SistemaConcessionaria.core.gateway.ClienteVendaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;

import java.util.UUID;

public class BuscarClienteVendaPorIdUseCaseImpl implements BuscarClienteVendaPorIdUseCase {

    private final ClienteVendaGateway gateway;

    public BuscarClienteVendaPorIdUseCaseImpl(ClienteVendaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public ClienteVenda execute(UUID id) {

        var existente = gateway.buscarVendaPorId(id);

        if (existente == null) {
            throw new ClienteVendaNaoEncontradaException(id);
        }

        return existente;
    }
}
