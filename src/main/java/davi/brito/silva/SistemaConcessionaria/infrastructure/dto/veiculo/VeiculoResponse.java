package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo;

import davi.brito.silva.SistemaConcessionaria.core.enums.StatusVeiculo;

import java.time.LocalDateTime;
import java.util.UUID;

public record VeiculoResponse(
        UUID id,
        String tipoVeiculo,
        String nome,
        String marca,
        Integer ano,
        Double valor,
        UUID idConcessionaria,
        StatusVeiculo statusVeiculo,
        LocalDateTime dataCadastro,
        LocalDateTime ultimaAtualizacao) {
}
