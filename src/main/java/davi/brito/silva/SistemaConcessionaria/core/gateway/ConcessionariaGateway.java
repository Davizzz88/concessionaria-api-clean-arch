package davi.brito.silva.SistemaConcessionaria.core.gateway;

import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;

import java.util.UUID;

public interface ConcessionariaGateway {

    Concessionaria criarConcessionaria(Concessionaria concessionaria);

    Concessionaria atualizarConcessionaria(Concessionaria concessionaria);

    Concessionaria removerConcessionaria(Concessionaria concessionaria);

    Concessionaria buscarConcessionariaPorId(UUID id);
}
