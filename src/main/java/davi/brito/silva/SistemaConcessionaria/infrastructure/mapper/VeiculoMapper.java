package davi.brito.silva.SistemaConcessionaria.infrastructure.mapper;

import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VeiculoMapper {

    Veiculo toDomain(VeiculoEntity veiculoEntity);

    VeiculoEntity toEntity(Veiculo veiculo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "ultimaAtualizacao", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    @Mapping(target = "statusVeiculo", ignore = true)
    VeiculoEntity toRequest(VeiculoRequest veiculoRequest);

    @Mapping(target = "idConcessionaria", source = "concessionaria.id")
    VeiculoResponse toResponse(Veiculo veiculo);

}
