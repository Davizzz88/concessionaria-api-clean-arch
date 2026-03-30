package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ConcessionariaAtualizarRequest(
        @NotBlank(message = "O campo de nome é obrigatorio")
        @Size(max = 50, message = "O nome deve conter no máximo 50 caracteres")
        String nome,
        @NotBlank(message = "O campo de cnpj é obrigatorio")
        @Size(max = 18, min = 18, message = "O cnpj deve estar no formato xx.xxx.xxx/xxxx-xx")
        String cnpj,
        @NotBlank(message = "Estado é obrigatório")
        @Size(min = 2, max = 2, message = "Estado deve ter 2 caracteres (ex: SP)")
        String estado,
        @NotBlank(message = "Cidade é obrigatório")
        @Size(max = 50, message = "Cidade deve conter no máximo 50 caracteres")
        String cidade,
        @NotBlank(message = "O campo de telefone é obrigatorio")
        @Size(max = 14, min = 14,  message = "O telefone deve estar no formato (xx)xxxxx-xxxx")
        String telefone) {
}
