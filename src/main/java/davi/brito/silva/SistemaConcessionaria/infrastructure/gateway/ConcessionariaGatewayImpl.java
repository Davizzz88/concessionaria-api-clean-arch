package davi.brito.silva.SistemaConcessionaria.infrastructure.gateway;

import davi.brito.silva.SistemaConcessionaria.core.exceptions.model.ConcessionariaNaoEncontradaException;
import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.ConcessionariaMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.ConcessionariaRepository;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConcessionariaGatewayImpl implements ConcessionariaGateway {

    private final ConcessionariaRepository repositoryConcessionaria;
    private final ConcessionariaMapper concessionariaMapper;

    @Override
    public Concessionaria criarConcessionaria(Concessionaria concessionaria) {
        ConcessionariaEntity concessionariaEntity = repositoryConcessionaria.save(concessionariaMapper.toEntity(concessionaria));
        return concessionariaMapper.toDomain(concessionariaEntity);
    }

    @Override
    public Concessionaria atualizarConcessionaria(Concessionaria concessionaria) {
        var cso = repositoryConcessionaria.findById(concessionaria.id())
                .orElseThrow(() -> new ConcessionariaNaoEncontradaException(concessionaria.id()));
        var domain = concessionariaMapper.toEntity(concessionaria);
        domain.setSenha(cso.getSenha());
        return concessionariaMapper.toDomain(repositoryConcessionaria.save(domain));
    }

    @Override
    public Concessionaria removerConcessionaria(Concessionaria concessionaria) {

        repositoryConcessionaria.findById(concessionaria.id()).
                orElseThrow(() -> new ConcessionariaNaoEncontradaException(concessionaria.id()));

        repositoryConcessionaria.deleteByIdAndNome(concessionaria.id(),concessionaria.nome());

        return concessionaria;
    }


    @Override
    public Concessionaria buscarConcessionariaPorId(UUID id) {
        return repositoryConcessionaria.findById(id).map(concessionariaMapper::toDomain).
                orElseThrow(() -> new ConcessionariaNaoEncontradaException(id));
    }
}
