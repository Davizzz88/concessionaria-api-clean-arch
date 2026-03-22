package davi.brito.silva.SistemaConcessionaria.infrastructure.gateway;

import davi.brito.silva.SistemaConcessionaria.core.enums.StatusVeiculo;
import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ClienteVendaMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ConcessionariaMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.VeiculoMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.VendedorMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.ClienteVendaRepository;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.ConcessionariaRepository;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.VeiculoRepository;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.VendedorRepository;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class ConcessionariaGatewayImpl implements ConcessionariaGateway {

    private final ConcessionariaRepository repositoryConcessionaria;
    private final VendedorRepository repositoryVendedor;
    private final VeiculoRepository repositoryVeiculo;
    private final ClienteVendaRepository repositoryClienteVenda;
    private final ConcessionariaMapper concessionariaMapper;
    private final VendedorMapper vendedorMapper;
    private final VeiculoMapper veiculoMapper;
    private final ClienteVendaMapper clienteVendaMapper;

    public ConcessionariaGatewayImpl(ConcessionariaRepository repositoryConcessionaria,
                                     VendedorRepository repositoryVendedor,
                                     VeiculoRepository repositoryVeiculo,
                                     ClienteVendaRepository repositoryClienteVenda,
                                     ConcessionariaMapper concessionariaMapper,
                                     VendedorMapper vendedorMapper,
                                     VeiculoMapper veiculoMapper,
                                     ClienteVendaMapper clienteVendaMapper) {
        this.repositoryConcessionaria = repositoryConcessionaria;
        this.repositoryVendedor = repositoryVendedor;
        this.repositoryVeiculo = repositoryVeiculo;
        this.repositoryClienteVenda = repositoryClienteVenda;
        this.concessionariaMapper = concessionariaMapper;
        this.vendedorMapper = vendedorMapper;
        this.veiculoMapper = veiculoMapper;
        this.clienteVendaMapper = clienteVendaMapper;
    }



    @Override
    public Concessionaria criarConcessionaria(Concessionaria concessionaria) {
        ConcessionariaEntity concessionariaEntity = repositoryConcessionaria.save(concessionariaMapper.toEntity(concessionaria));
        return concessionariaMapper.toDomain(concessionariaEntity);
    }

    @Override
    public Concessionaria atualizarConcessionaria(Concessionaria concessionaria) {
        return concessionariaMapper.toDomain(repositoryConcessionaria.save(concessionariaMapper.toEntity(concessionaria)));
    }

    @Override
    public Concessionaria removerConcessionaria(Concessionaria concessionaria) {

        var existente = repositoryConcessionaria.findById(concessionaria.id());

        if (existente.isEmpty()){
            throw new RuntimeException("Concessionária não encontrada");
        }

        repositoryConcessionaria.deleteByIdAndNome(concessionaria.id(),concessionaria.nome());

        return concessionaria;
    }


    @Override
    public Concessionaria buscarConcessionariaPorId(UUID id) {
        return repositoryConcessionaria.findById(id).map(concessionariaMapper::toDomain).orElse(null);
    }

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
        return repositoryVendedor.findById(id).map(vendedorMapper::toDomain).orElse(null);
    }

    @Override
    public Veiculo criarVeiculo(Veiculo veiculo) {
        VeiculoEntity veiculoEntity = repositoryVeiculo.save(veiculoMapper.toEntity(veiculo));
        return veiculoMapper.toDomain(veiculoEntity);
    }

    @Override
    public Veiculo atualizarVeiculo(Veiculo veiculo) {
        return veiculoMapper.toDomain(repositoryVeiculo.save(veiculoMapper.toEntity(veiculo)));
    }

    @Override
    public Veiculo removerVeiculo(Veiculo veiculo) {

        var existente = repositoryVeiculo.findById(veiculo.id());

        if (existente.isEmpty()){
            throw new RuntimeException("Veiculo não encontrado");
        }

        repositoryVeiculo.deleteByIdAndNome(veiculo.id(),veiculo.nome());

        return veiculo;
    }

    @Override
    public Veiculo buscarVeiculoPorId(UUID id) {
        return repositoryVeiculo.findById(id).map(veiculoMapper::toDomain).orElse(null);
    }

    @Override
    public ClienteVenda criarClienteVenda(ClienteVenda clienteVenda, UUID idVeiculo) {

        var existente = repositoryVeiculo.findById(idVeiculo);

        if (existente.isEmpty()){
            throw new RuntimeException("Veiculo não encontrado");
        }

        var veiculoEntity = existente.get();

        veiculoEntity.setStatusVeiculo(StatusVeiculo.VENDIDO);

        repositoryVeiculo.save(veiculoEntity);

        var clienteVendaEntity = clienteVendaMapper.toEntity(clienteVenda);

        clienteVendaEntity.setVeiculo(veiculoEntity);

        return clienteVendaMapper.toDomain(repositoryClienteVenda.save(clienteVendaEntity));
    }

    @Override
    public ClienteVenda buscarVendaPorId(UUID id) {
        return repositoryClienteVenda.findById(id).map(clienteVendaMapper::toDomain).orElse(null);
    }
}
