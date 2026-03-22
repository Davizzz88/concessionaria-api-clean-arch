package davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

import java.util.UUID;

public class BuscarVeiculoPorIdUseCaseImpl implements BuscarVeiculoPorIdUseCase {

    private final ConcessionariaGateway gateway;

    public BuscarVeiculoPorIdUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Veiculo execute(UUID id) {

        var existente = gateway.buscarVeiculoPorId(id);

        if (existente == null) {
            throw new RuntimeException("Veiculo não encontrada");
        }

        return existente;
    }
}
