package davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.remover;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

public class RemoverVeiculoUseCaseImpl implements RemoverVeiculoUseCase{

    private final ConcessionariaGateway gateway;

    public RemoverVeiculoUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Veiculo execute(Veiculo veiculo) {

        var existente = gateway.buscarVeiculoPorId(veiculo.id());

        if (existente == null){
            throw new RuntimeException("Veiculo não encontrada");
        }

        return gateway.removerVeiculo(veiculo);
    }
}
