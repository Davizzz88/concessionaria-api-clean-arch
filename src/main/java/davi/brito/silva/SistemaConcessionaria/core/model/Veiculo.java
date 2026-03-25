package davi.brito.silva.SistemaConcessionaria.core.model;

import davi.brito.silva.SistemaConcessionaria.core.enums.StatusVeiculo;

import java.time.LocalDateTime;
import java.util.UUID;

public record Veiculo(
        UUID id,
        String tipoVeiculo,
        String nome,
        String marca,
        Integer ano,
        Double valor,
        Concessionaria concessionaria,
        StatusVeiculo statusVeiculo,
        ClienteVenda vendas,
        LocalDateTime dataCadastro,
        LocalDateTime ultimaAtualizacao) {
}
