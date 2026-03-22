package davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;

import java.util.UUID;

public interface BuscarConcessionatiaPorIdUseCase {

    Concessionaria execute(UUID id);

}
