package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor;

import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import jakarta.validation.constraints.*;

public record VendedorRequest(
        @NotBlank(message = "O campo de nome é obrigatorio")
        @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
        String nome,
        @NotBlank(message = "O campo de cpf é obrigatorio")
        @Size(max = 14, min = 14, message = "O cpf deve estar no formato xxx.xxx.xxx-xx")
        String cpf,
        @NotNull(message = "O campo de idade é obrigatorio")
        @Positive(message = "A idade deve ser um número positivo")
        Integer idade,
        @NotBlank(message = "O campo de telefone é obrigatorio")
        @Size(max = 14, min = 14, message = "O telefone deve estar no formato (xx)xxxxx-xxxx")
        String telefone,
        @NotNull(message = "O campo de comissão é obrigatorio")
        @Positive(message = "A comissão deve ser um número positivo")
        @Min(value = 0, message = "A comissão deve ser no mínimo 0%")
        @Max(value = 100, message = "A comissão deve ser no máximo 100%")
        Integer porcentagemComissao,
        @NotNull(message = "O campo de concessionaria é obrigatorio")
        Concessionaria concessionaria) {
}
