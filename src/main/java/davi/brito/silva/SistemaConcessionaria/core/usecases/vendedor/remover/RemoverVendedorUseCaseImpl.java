package davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.remover;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

public class RemoverVendedorUseCaseImpl implements RemoverVendedorUseCase{

    private final ConcessionariaGateway gateway;

    public RemoverVendedorUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Vendedor execute(Vendedor vendedor) {

        var existente = gateway.buscarVendedorPorId(vendedor.id());

        if (existente == null){
            throw new RuntimeException("Vendedor não encontrada");
        }

        return gateway.removerVendedor(vendedor);
    }
}
