package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor;

import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record VendedorResponse(
        UUID id,
        String nome,
        String cpf,
        Integer idade,
        String telefone,
        Integer porcentagemComissao,
        UUID idConcessionaria,
        List<ClienteVendaResponse> vendas,
        LocalDateTime dataCadastro,
        LocalDateTime ultimaAtualizacao) {
}
