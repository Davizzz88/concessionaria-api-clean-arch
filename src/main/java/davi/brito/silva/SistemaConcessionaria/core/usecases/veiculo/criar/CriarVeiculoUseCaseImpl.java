package davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.criar;

import davi.brito.silva.SistemaConcessionaria.core.gateway.VeiculoGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

public class CriarVeiculoUseCaseImpl implements CriarVeiculoUseCase{

    private final VeiculoGateway gateway;

    public CriarVeiculoUseCaseImpl(VeiculoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Veiculo execute(Veiculo veiculo) {
        return gateway.criarVeiculo(veiculo);
    }
}
