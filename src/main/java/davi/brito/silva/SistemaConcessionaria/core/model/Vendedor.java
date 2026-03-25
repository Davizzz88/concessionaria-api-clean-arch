package davi.brito.silva.SistemaConcessionaria.core.model;

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
        Concessionaria concessionaria,
        List<ClienteVenda> vendas,
        LocalDate dataCadastro,
        LocalDateTime ultimaAtualizacao) {
}
