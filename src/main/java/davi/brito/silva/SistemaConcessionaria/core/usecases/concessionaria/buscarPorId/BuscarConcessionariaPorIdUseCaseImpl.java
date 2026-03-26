package davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.ConcessionariaNaoEncontradaException;
import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;

import java.util.UUID;

public class BuscarConcessionariaPorIdUseCaseImpl implements BuscarConcessionariaPorIdUseCase {

    private final ConcessionariaGateway gateway;

    public BuscarConcessionariaPorIdUseCaseImpl(ConcessionariaGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public Concessionaria execute(UUID id) {

        var existente = gateway.buscarConcessionariaPorId(id);

        if (existente == null) {
            throw new ConcessionariaNaoEncontradaException(id);
        }

        return existente;
    }
}
