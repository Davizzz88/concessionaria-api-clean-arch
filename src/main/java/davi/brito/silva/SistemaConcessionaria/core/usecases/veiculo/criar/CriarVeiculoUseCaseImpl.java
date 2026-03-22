package davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.criar;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

public class CriarVeiculoUseCaseImpl implements CriarVeiculoUseCase{

    private final ConcessionariaGateway gateway;

    public CriarVeiculoUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Veiculo execute(Veiculo veiculo) {
        return gateway.criarVeiculo(veiculo);
    }
}
