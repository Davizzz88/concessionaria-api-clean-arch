package davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.remover;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.ConcessionariaNaoEncontradaException;
import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;

public class RemoverConcessionariaUseCaseImpl implements RemoverConcessionariaUseCase{

    private final ConcessionariaGateway gateway;

    public RemoverConcessionariaUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public Concessionaria execute(Concessionaria concessionaria) {

        var existente = gateway.buscarConcessionariaPorId(concessionaria.id());

        if (existente == null){
            throw new ConcessionariaNaoEncontradaException(concessionaria.id());
        }

        return gateway.removerConcessionaria(concessionaria);
    }
}
