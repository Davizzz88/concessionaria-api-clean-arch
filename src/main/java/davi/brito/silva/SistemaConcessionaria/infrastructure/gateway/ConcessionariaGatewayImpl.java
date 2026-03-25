package davi.brito.silva.SistemaConcessionaria.infrastructure.gateway;

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
        return repositoryConcessionaria.findById(id).map(concessionariaMapper::toDomain).
                orElseThrow(() -> new RuntimeException("Concessionária não encontrada"));
    }
}
