package davi.brito.silva.SistemaConcessionaria.core.exceptions.model;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.NotFoundException;

import java.util.UUID;

public class VeiculoNaoEncontradoException extends NotFoundException {
    public VeiculoNaoEncontradoException(UUID id) {
        super("Veiculo com id (" + id + ") não encontrado");
    }
}
