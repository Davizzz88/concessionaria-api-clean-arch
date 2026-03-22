package davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

import java.util.UUID;

public interface BuscarVeiculoPorIdUseCase {

    Veiculo execute(UUID id);

}
