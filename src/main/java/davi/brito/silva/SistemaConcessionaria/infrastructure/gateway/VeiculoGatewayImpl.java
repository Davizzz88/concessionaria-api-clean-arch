package davi.brito.silva.SistemaConcessionaria.infrastructure.gateway;

import davi.brito.silva.SistemaConcessionaria.core.gateway.VeiculoGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.infrastructure.mapper.VeiculoMapper;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VeiculoGatewayImpl implements VeiculoGateway {

    private final VeiculoRepository repositoryVeiculo;
    private final VeiculoMapper veiculoMapper;


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
        return repositoryVeiculo.findById(id).map(veiculoMapper::toDomain).
                orElseThrow(() -> new RuntimeException("Veiculo não encontrado"));
    }


}
