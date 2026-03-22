package davi.brito.silva.SistemaConcessionaria.core.model;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record Concessionaria(
        UUID id,
        String nome,
        String cnpj,
        String estado,
        String cidade,
        String telefone,
        List<VendedorEntity> vendedor,
        List<VeiculoEntity> veiculo,
        LocalDateTime ultimaAtualizacao) {
}
