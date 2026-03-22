package davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.criar;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;

import java.util.UUID;

public interface CriarClienteVendaUseCase {

    ClienteVenda execute(ClienteVenda clienteVenda, UUID idVeiculo);

}
