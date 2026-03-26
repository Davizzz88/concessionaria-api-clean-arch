package davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.remover;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.VeiculoNaoEncontradoException;
import davi.brito.silva.SistemaConcessionaria.core.gateway.VeiculoGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

public class RemoverVeiculoUseCaseImpl implements RemoverVeiculoUseCase{

    private final VeiculoGateway gateway;

    public RemoverVeiculoUseCaseImpl(VeiculoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Veiculo execute(Veiculo veiculo) {

        var existente = gateway.buscarVeiculoPorId(veiculo.id());

        if (existente == null){
            throw new VeiculoNaoEncontradoException(veiculo.id());
        }

        return gateway.removerVeiculo(veiculo);
    }
}
