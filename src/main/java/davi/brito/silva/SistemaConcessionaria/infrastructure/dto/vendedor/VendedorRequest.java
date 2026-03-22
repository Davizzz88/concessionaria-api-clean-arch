package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;

public record VendedorRequest(
        String nome,
        String cpf,
        Integer idade,
        String telefone,
        Integer porcentagemComissao,
        ConcessionariaEntity concessionaria) {
}
