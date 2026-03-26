package davi.brito.silva.SistemaConcessionaria.core.exceptions.model;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.NotFoundException;

import java.util.UUID;

public class VendedorNaoEncontradoException extends NotFoundException {
    public VendedorNaoEncontradoException(UUID id) {
        super("Vendedor com id (" + id + ") não encontrado");
    }
}
