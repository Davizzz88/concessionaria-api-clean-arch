package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;

public record VeiculoRequest(
        String tipoVeiculo,
        String nome,
        String marca,
        Integer ano,
        Double valor,
        ConcessionariaEntity concessionaria) {
}
