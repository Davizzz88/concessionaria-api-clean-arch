package davi.brito.silva.SistemaConcessionaria.infrastructure.mapper;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ClienteVendaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ClienteVendaMapper {

    @Mapping(target = "vendedor", qualifiedByName = "vendedorSemCiclo")
    @Mapping(target = "veiculo", qualifiedByName = "veiculoSemCiclo")
    ClienteVenda toDomain(ClienteVendaEntity clienteVendaEntity);

    @Named("vendedorSemCiclo")
    @Mapping(target = "concessionaria", qualifiedByName = "concessionariaApenasId")  // ← manter o ID!
    @Mapping(target = "vendas", ignore = true)
    Vendedor vendedorSemCiclo(VendedorEntity entity);

    @Named("veiculoSemCiclo")
    @Mapping(target = "concessionaria", qualifiedByName = "concessionariaApenasId")  // ← manter o ID!
    @Mapping(target = "vendas", ignore = true)
    Veiculo veiculoSemCiclo(VeiculoEntity entity);

    @Named("concessionariaApenasId")
    @Mapping(target = "vendedor", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    @Mapping(target = "senha", ignore = true)
    Concessionaria concessionariaApenasId(ConcessionariaEntity entity);

    ClienteVendaEntity toEntity(ClienteVenda clienteVenda);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataVenda", ignore = true)
    ClienteVendaEntity toRequest(ClienteVendaRequest clienteVendaRequest);

    @Mapping(target = "idVeiculo", source = "veiculo.id")
    @Mapping(target = "idVendedor", source = "vendedor.id")
    ClienteVendaResponse toResponse(ClienteVenda clienteVenda);
}
