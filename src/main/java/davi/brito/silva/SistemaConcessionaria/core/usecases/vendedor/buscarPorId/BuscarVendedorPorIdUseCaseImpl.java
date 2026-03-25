package davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.gateway.VendedorGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

import java.util.UUID;

public class BuscarVendedorPorIdUseCaseImpl implements BuscarVendedorPorIdUseCase{

    private final VendedorGateway gateway;

    public BuscarVendedorPorIdUseCaseImpl(VendedorGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Vendedor execute(UUID id) {

        var existente = gateway.buscarVendedorPorId(id);

        if (existente == null) {
            throw new RuntimeException("Vendedor não encontrado");
        }

        return existente;
    }
}
