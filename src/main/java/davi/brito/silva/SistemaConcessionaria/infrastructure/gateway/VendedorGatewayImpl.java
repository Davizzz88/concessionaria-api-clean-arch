package davi.brito.silva.SistemaConcessionaria.infrastructure.gateway;

import davi.brito.silva.SistemaConcessionaria.core.gateway.VendedorGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.VendedorMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VendedorGatewayImpl implements VendedorGateway {

    private final VendedorRepository repositoryVendedor;
    private final VendedorMapper vendedorMapper;

    @Override
    public Vendedor criarVendedor(Vendedor vendedor) {
        VendedorEntity vendedorEntity = repositoryVendedor.save(vendedorMapper.toEntity(vendedor));
        return vendedorMapper.toDomain(vendedorEntity);
    }

    @Override
    public Vendedor atualizarVendedor(Vendedor vendedor) {
        return vendedorMapper.toDomain(repositoryVendedor.save(vendedorMapper.toEntity(vendedor)));
    }

    @Override
    public Vendedor removerVendedor(Vendedor vendedor) {

        var existente = repositoryVendedor.findById(vendedor.id());

        if (existente.isEmpty()){
            throw new RuntimeException("Vendedor não encontrado");
        }

        repositoryVendedor.deleteByIdAndCpf(vendedor.id(),vendedor.cpf());

        return vendedor;
    }

    @Override
    public Vendedor buscarVendedorPorId(UUID id) {
        return repositoryVendedor.findById(id).map(vendedorMapper::toDomain).
                orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
    }
}
