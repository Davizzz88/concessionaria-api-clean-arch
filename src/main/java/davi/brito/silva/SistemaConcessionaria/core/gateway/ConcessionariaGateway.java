package davi.brito.silva.SistemaConcessionaria.core.gateway;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

import java.util.UUID;

public interface ConcessionariaGateway {

    Concessionaria criarConcessionaria(Concessionaria concessionaria);

    Concessionaria atualizarConcessionaria(Concessionaria concessionaria);

    Concessionaria removerConcessionaria(Concessionaria concessionaria);

    Concessionaria buscarConcessionariaPorId(UUID id);

    Vendedor criarVendedor(Vendedor vendedor);

    Vendedor atualizarVendedor(Vendedor vendedor);

    Vendedor removerVendedor(Vendedor vendedor);

    Vendedor buscarVendedorPorId(UUID id);

    Veiculo criarVeiculo(Veiculo veiculo);

    Veiculo atualizarVeiculo(Veiculo veiculo);

    Veiculo removerVeiculo(Veiculo veiculo);

    Veiculo buscarVeiculoPorId(UUID id);

    ClienteVenda criarClienteVenda(ClienteVenda clienteVenda, UUID idVeiculo);

    ClienteVenda buscarVendaPorId(UUID id);

}
