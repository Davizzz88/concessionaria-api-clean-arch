package davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.remover;

import davi.brito.silva.SistemaConcessionaria.core.gateway.VendedorGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

public class RemoverVendedorUseCaseImpl implements RemoverVendedorUseCase{

    private final VendedorGateway gateway;

    public RemoverVendedorUseCaseImpl(VendedorGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Vendedor execute(Vendedor vendedor) {

        var existente = gateway.buscarVendedorPorId(vendedor.id());

        if (existente == null){
            throw new RuntimeException("Vendedor não encontrado");
        }

        return gateway.removerVendedor(vendedor);
    }
}
