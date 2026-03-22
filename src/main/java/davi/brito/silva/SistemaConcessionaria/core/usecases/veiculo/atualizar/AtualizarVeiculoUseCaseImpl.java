package davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.atualizar;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;

import java.time.LocalDateTime;

public class AtualizarVeiculoUseCaseImpl implements AtualizarVeiculoUseCase {

    private final ConcessionariaGateway geteway;

    public AtualizarVeiculoUseCaseImpl(ConcessionariaGateway geteway) {
        this.geteway = geteway;
    }

    @Override
    public Veiculo execute(Veiculo veiculo) {
        var existente = geteway.buscarVeiculoPorId(veiculo.id());

        if (existente == null){
            throw new RuntimeException("Veiculo não encontrado");
        }
        return geteway.atualizarVeiculo(new Veiculo(
                existente.id(),
                veiculo.tipoVeiculo(),
                veiculo.nome(),
                veiculo.marca(),
                veiculo.ano(),
                veiculo.valor(),
                existente.concessionaria(),
                existente.statusVeiculo(),
                existente.vendas(),
                existente.dataCadastro(),
                LocalDateTime.now()
        ));
    }
}
