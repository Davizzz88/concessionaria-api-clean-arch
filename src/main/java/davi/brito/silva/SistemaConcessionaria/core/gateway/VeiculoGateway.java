package davi.brito.silva.SistemaConcessionaria.core.gateway;

import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

import java.util.UUID;

public interface VeiculoGateway {

    Veiculo criarVeiculo(Veiculo veiculo);

    Veiculo atualizarVeiculo(Veiculo veiculo);

    Veiculo removerVeiculo(Veiculo veiculo);

    Veiculo buscarVeiculoPorId(UUID id);

}
