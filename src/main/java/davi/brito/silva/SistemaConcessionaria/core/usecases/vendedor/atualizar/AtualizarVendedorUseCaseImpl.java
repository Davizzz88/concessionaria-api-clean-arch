package davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.atualizar;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.VendedorNaoEncontradoException;
import davi.brito.silva.SistemaConcessionaria.core.gateway.VendedorGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;

import java.time.LocalDateTime;

public class AtualizarVendedorUseCaseImpl implements AtualizarVendedorUseCase{

    private final VendedorGateway geteway;

    public AtualizarVendedorUseCaseImpl(VendedorGateway geteway) {
        this.geteway = geteway;
    }

    @Override
    public Vendedor execute(Vendedor vendedor) {
        var existente = geteway.buscarVendedorPorId(vendedor.id());

        if (existente == null){
            throw new VendedorNaoEncontradoException(vendedor.id());
        }
        return geteway.atualizarVendedor(new Vendedor(
                existente.id(),
                vendedor.nome(),
                vendedor.cpf(),
                vendedor.idade(),
                vendedor.telefone(),
                vendedor.porcentagemComissao(),
                existente.concessionaria(),
                existente.vendas(),
                existente.dataCadastro(),
                LocalDateTime.now()
        ));
    }
}
