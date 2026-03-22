package davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.buscarPorId;

import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

import java.util.UUID;

public interface BuscarVendedorPorIdUseCase {

    Vendedor execute(UUID id);

}
