package davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.criar;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

public class CriarVendedorUseCaseImpl implements CriarVendedorUseCase{

    private final ConcessionariaGateway gateway;

    public CriarVendedorUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Vendedor execute(Vendedor vendedor) {
        return gateway.criarVendedor(vendedor);
    }
}
