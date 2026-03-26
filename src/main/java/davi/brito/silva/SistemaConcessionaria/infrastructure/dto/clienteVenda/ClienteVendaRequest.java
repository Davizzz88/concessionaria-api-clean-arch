package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda;

import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteVendaRequest(
        @NotBlank(message = "O campo de nome é obrigatorio")
        @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
        String nomeCliente,
        @NotBlank(message = "O campo de cpf é obrigatorio")
        @Size(max = 14, min = 14,  message = "O cpf deve estar no formato xxx.xxx.xxx-xx")
        String cpfCliente,
        @NotBlank(message = "O campo de telefone é obrigatorio")
        @Size(max = 14, min = 14,  message = "O telefone deve estar no formato (xx)xxxxx-xxxx")
        String telefoneCliente,
        @NotNull(message = "Adicione um veiculo válido")
        Veiculo veiculo,
        @NotNull(message = "Adicione um vendedor válido")
        Vendedor vendedor,
        @NotBlank(message = "Adicione um tipo de pagamento válido")
        @Size(max = 20, message = "O tipo de pagamento deve conter no máximo 20 caracteres")
        String tipoPagamento) {
}
