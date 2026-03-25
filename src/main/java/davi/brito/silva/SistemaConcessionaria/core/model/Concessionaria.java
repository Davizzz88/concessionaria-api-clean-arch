package davi.brito.silva.SistemaConcessionaria.core.model;

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
        List<Vendedor> vendedor,
        List<Veiculo> veiculo,
        LocalDateTime ultimaAtualizacao) {
}
