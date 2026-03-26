package davi.brito.silva.SistemaConcessionaria.core.exceptions.model;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.NotFoundException;

import java.util.UUID;

public class ClienteVendaNaoEncontradaException extends NotFoundException {
    public ClienteVendaNaoEncontradaException(UUID id) {
        super("Venda com id (" + id + ") não encontrada");
    }
}
