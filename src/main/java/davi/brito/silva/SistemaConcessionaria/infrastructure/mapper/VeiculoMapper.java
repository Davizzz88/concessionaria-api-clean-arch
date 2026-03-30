package davi.brito.silva.SistemaConcessionaria.infrastructure.mapper;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ClienteVendaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface VeiculoMapper {

    @Mapping(target = "concessionaria", qualifiedByName = "concessionariaSemCiclo")
    @Mapping(target = "vendas", qualifiedByName = "vendaSemCicloVeiculo")
    Veiculo toDomain(VeiculoEntity veiculoEntity);

    @Named("vendaSemCicloVeiculo")
    @Mapping(target = "veiculo", ignore = true)
    @Mapping(target = "vendedor", ignore = true)
    ClienteVenda vendaSemCicloVeiculo(ClienteVendaEntity entity);

    @Named("concessionariaSemCiclo")
    @Mapping(target = "vendedor", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    Concessionaria concessionariaSemCiclo(ConcessionariaEntity entity);

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
