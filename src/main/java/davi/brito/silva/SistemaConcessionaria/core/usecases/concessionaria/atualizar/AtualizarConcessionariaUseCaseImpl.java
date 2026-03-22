package davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;

import java.time.LocalDateTime;

public class AtualizarConcessionariaUseCaseImpl implements AtualizarConcessionariaUseCase {

    private final ConcessionariaGateway geteway;

    public AtualizarConcessionariaUseCaseImpl(ConcessionariaGateway geteway) {
        this.geteway = geteway;
    }

    @Override
    public Concessionaria execute(Concessionaria concessionaria) {

        var existente = geteway.buscarConcessionariaPorId(concessionaria.id());

        if (existente == null){
            throw new RuntimeException("Concessionária não encontrada");
        }
        return geteway.atualizarConcessionaria(new Concessionaria(
                existente.id(),
                concessionaria.nome(),
                concessionaria.cnpj(),
                concessionaria.estado(),
                concessionaria.cidade(),
                concessionaria.telefone(),
                existente.vendedor(),
                existente.veiculo(),
                LocalDateTime.now()
        ));
    }
}
