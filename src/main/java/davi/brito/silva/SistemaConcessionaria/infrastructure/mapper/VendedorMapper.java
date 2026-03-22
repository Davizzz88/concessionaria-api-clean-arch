package davi.brito.silva.SistemaConcessionaria.infrastructure.mapper;

import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = ClienteVendaMapper.class)
public interface VendedorMapper {

    Vendedor toDomain(VendedorEntity vendedorEntity);

    VendedorEntity toEntity(Vendedor vendedor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "ultimaAtualizacao", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    VendedorEntity toRequest(VendedorRequest vendedorRequest);

    @Mapping(target = "idConcessionaria", source = "concessionaria.id")
    VendedorResponse toResponse(Vendedor vendedor);

}
