package davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;

import java.util.UUID;

public class BuscarClienteVendaPorIdUseCaseImpl implements BuscarClienteVendaPorIdUseCase {

    private final ConcessionariaGateway gateway;

    public BuscarClienteVendaPorIdUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public ClienteVenda execute(UUID id) {

        var existente = gateway.buscarVendaPorId(id);

        if (existente == null) {
            throw new RuntimeException("Venda não encontrada");
        }

        return existente;
    }
}
