package davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria;

public record ConcessionariaRequest(
        String nome,
        String cnpj,
        String estado,
        String cidade,
        String telefone) {
}
