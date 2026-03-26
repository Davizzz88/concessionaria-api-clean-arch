package davi.brito.silva.SistemaConcessionaria.infrastructure.controller.handler;

import java.time.LocalDateTime;

public record ErrorResponse(
        int status,
        String mensagem,
        LocalDateTime timestamp
) {
}
