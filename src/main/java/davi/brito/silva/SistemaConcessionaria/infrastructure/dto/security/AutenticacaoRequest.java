package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutenticacaoRequest(
        @NotBlank(message = "O campo de nome é obrigatorio")
        @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
        String nome,
        @NotBlank
        @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
        String senha) {
}
