package davi.brito.silva.SistemaConcessionaria.core.model;

import davi.brito.silva.SistemaConcessionaria.core.enums.StatusVeiculo;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ClienteVendaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record Veiculo(
        UUID id,
        String tipoVeiculo,
        String nome,
        String marca,
        Integer ano,
        Double valor,
        ConcessionariaEntity concessionaria,
        StatusVeiculo statusVeiculo,
        ClienteVendaEntity vendas,
        LocalDateTime dataCadastro,
        LocalDateTime ultimaAtualizacao) {
}
