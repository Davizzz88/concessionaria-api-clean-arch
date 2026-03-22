package davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;

import java.util.UUID;

public interface BuscarClienteVendaPorIdUseCase {

    ClienteVenda execute(UUID id);

}
