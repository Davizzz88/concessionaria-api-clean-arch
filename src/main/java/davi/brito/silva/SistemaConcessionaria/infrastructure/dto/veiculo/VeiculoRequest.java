package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo;

import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import jakarta.validation.constraints.*;

public record VeiculoRequest(
        @NotBlank(message = "O campo de tipo de veiculo é obrigatorio")
        @Size(max = 15, message = "O tipo de veiculo deve conter no máximo 15 caracteres")
        String tipoVeiculo,
        @NotBlank(message = "O campo de nome é obrigatorio")
        @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
        String nome,
        @NotBlank(message = "O campo de marca é obrigatorio")
        @Size(max = 20, message = "A marca deve conter no máximo 20 caracteres")
        String marca,
        @NotNull(message = "O campo de ano é obrigatorio")
        @Positive(message = "O ano deve ser um número positivo")
        @Max(value = 9999, message = "O ano deve conter no máximo 4 dígitos")
        Integer ano,
        @NotNull(message = "O campo de valor é obrigatorio")
        @Positive(message = "O valor deve ser um número positivo")
        Double valor,
        @NotNull(message = "O campo de concessionaria é obrigatorio")
        Concessionaria concessionaria) {
}
