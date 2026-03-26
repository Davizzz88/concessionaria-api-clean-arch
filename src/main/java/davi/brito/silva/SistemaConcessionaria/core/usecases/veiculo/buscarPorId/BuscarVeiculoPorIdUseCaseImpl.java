package davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.VeiculoNaoEncontradoException;
import davi.brito.silva.SistemaConcessionaria.core.gateway.VeiculoGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

import java.util.UUID;

public class BuscarVeiculoPorIdUseCaseImpl implements BuscarVeiculoPorIdUseCase {

    private final VeiculoGateway gateway;

    public BuscarVeiculoPorIdUseCaseImpl(VeiculoGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Veiculo execute(UUID id) {

        var existente = gateway.buscarVeiculoPorId(id);

        if (existente == null) {
            throw new VeiculoNaoEncontradoException(id);
        }

        return existente;
    }
}
