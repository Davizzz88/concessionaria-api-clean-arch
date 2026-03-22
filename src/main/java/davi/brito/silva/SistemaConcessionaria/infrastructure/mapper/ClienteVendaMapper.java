package davi.brito.silva.SistemaConcessionaria.infrastructure.mapper;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ClienteVendaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ClienteVendaMapper {

    ClienteVenda toDomain(ClienteVendaEntity clienteVendaEntity);

    ClienteVendaEntity toEntity(ClienteVenda clienteVenda);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataVenda", ignore = true)
    ClienteVendaEntity toRequest(ClienteVendaRequest clienteVendaRequest);

    @Mapping(target = "idVeiculo", source = "veiculo.id")
    @Mapping(target = "idVendedor", source = "vendedor.id")
    ClienteVendaResponse toResponse(ClienteVenda clienteVenda);
}
