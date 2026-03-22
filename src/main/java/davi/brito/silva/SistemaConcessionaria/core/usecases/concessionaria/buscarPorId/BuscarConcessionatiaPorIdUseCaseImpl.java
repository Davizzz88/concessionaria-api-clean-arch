package davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;

import java.util.UUID;

public class BuscarConcessionatiaPorIdUseCaseImpl implements BuscarConcessionatiaPorIdUseCase{

    private final ConcessionariaGateway gateway;

    public BuscarConcessionatiaPorIdUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public Concessionaria execute(UUID id) {

        var existente = gateway.buscarConcessionariaPorId(id);

        if (existente == null) {
            throw new RuntimeException("Concessionária não encontrada");
        }

        return existente;
    }
}
