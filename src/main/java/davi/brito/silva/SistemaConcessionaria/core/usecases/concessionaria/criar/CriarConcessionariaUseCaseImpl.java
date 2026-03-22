package davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.criar;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;

public class CriarConcessionariaUseCaseImpl implements CriarConcessionariaUseCase{

    private final ConcessionariaGateway gateway;

    public CriarConcessionariaUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public Concessionaria execute(Concessionaria concessionaria) {
        return gateway.criarConcessionaria(concessionaria);
    }
}
