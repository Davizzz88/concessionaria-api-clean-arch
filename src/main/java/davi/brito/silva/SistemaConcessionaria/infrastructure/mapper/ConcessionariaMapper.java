package davi.brito.silva.SistemaConcessionaria.infrastructure.mapper;

import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = { VeiculoMapper.class, VendedorMapper.class })
public interface ConcessionariaMapper {

    Concessionaria toDomain(ConcessionariaEntity concessionariaEntity);

    ConcessionariaEntity toEntity(Concessionaria concessionaria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ultimaAtualizacao", ignore = true)
    @Mapping(target = "vendedor", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    ConcessionariaEntity toRequest(ConcessionariaRequest concessionariaRequest);

    ConcessionariaResponse toResponse(Concessionaria concessionaria);

}
