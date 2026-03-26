package davi.brito.silva.SistemaConcessionaria.core.exceptions.model;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.NotFoundException;

import java.util.UUID;

public class ConcessionariaNaoEncontradaException extends NotFoundException {
    public ConcessionariaNaoEncontradaException(UUID id) {
        super("Concessionaria com id (" + id + ") não encontrada");
    }
}
