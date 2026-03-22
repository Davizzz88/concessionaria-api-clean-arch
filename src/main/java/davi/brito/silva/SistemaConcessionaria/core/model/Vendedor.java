package davi.brito.silva.SistemaConcessionaria.core.model;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ClienteVendaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Vendedor(
        UUID id,
        String nome,
        String cpf,
        Integer idade,
        String telefone,
        Integer porcentagemComissao,
        ConcessionariaEntity concessionaria,
        List<ClienteVendaEntity> vendas,
        LocalDate dataCadastro,
        LocalDateTime ultimaAtualizacao) {
}
