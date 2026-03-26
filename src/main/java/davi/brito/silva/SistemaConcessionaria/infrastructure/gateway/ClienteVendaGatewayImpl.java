package davi.brito.silva.SistemaConcessionaria.infrastructure.gateway;

import davi.brito.silva.SistemaConcessionaria.core.enums.StatusVeiculo;
import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.ClienteVendaNaoEncontradaException;
import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.VeiculoNaoEncontradoException;
import davi.brito.silva.SistemaConcessionaria.core.gateway.ClienteVendaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ClienteVendaMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.ClienteVendaRepository;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ClienteVendaGatewayImpl implements ClienteVendaGateway {

    private final VeiculoRepository repositoryVeiculo;
    private final ClienteVendaRepository repositoryClienteVenda;
    private final ClienteVendaMapper clienteVendaMapper;

    @Override
    public ClienteVenda criarClienteVenda(ClienteVenda clienteVenda, UUID idVeiculo) {

        var existente = repositoryVeiculo.findById(idVeiculo).
                orElseThrow(() -> new VeiculoNaoEncontradoException(idVeiculo));


        existente.setStatusVeiculo(StatusVeiculo.VENDIDO);

        repositoryVeiculo.save(existente);

        var clienteVendaEntity = clienteVendaMapper.toEntity(clienteVenda);

        clienteVendaEntity.setVeiculo(existente);

        return clienteVendaMapper.toDomain(repositoryClienteVenda.save(clienteVendaEntity));
    }

    @Override
    public ClienteVenda buscarVendaPorId(UUID id) {
        return repositoryClienteVenda.findById(id).map(clienteVendaMapper::toDomain).
                orElseThrow(() -> new ClienteVendaNaoEncontradaException(id));
    }
}
