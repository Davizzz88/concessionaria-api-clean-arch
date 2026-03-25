package davi.brito.silva.SistemaConcessionaria.core.gateway;

import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

import java.util.UUID;

public interface VendedorGateway {

    Vendedor criarVendedor(Vendedor vendedor);

    Vendedor atualizarVendedor(Vendedor vendedor);

    Vendedor removerVendedor(Vendedor vendedor);

    Vendedor buscarVendedorPorId(UUID id);


}
