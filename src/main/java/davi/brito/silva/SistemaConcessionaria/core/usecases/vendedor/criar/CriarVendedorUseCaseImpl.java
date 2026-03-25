package davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.criar;

import davi.brito.silva.SistemaConcessionaria.core.gateway.VendedorGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

public class CriarVendedorUseCaseImpl implements CriarVendedorUseCase{

    private final VendedorGateway gateway;

    public CriarVendedorUseCaseImpl(VendedorGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Vendedor execute(Vendedor vendedor) {
        return gateway.criarVendedor(vendedor);
    }
}
