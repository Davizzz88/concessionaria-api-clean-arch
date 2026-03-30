package davi.brito.silva.SistemaConcessionaria.infrastructure.mapper;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.vendedor.VendedorResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ClienteVendaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface VendedorMapper {

    @Mapping(target = "concessionaria", qualifiedByName = "concessionariaSemCiclo")
    @Mapping(target = "vendas", qualifiedByName = "vendaSemCicloVendedor")
    Vendedor toDomain(VendedorEntity vendedorEntity);

    @Named("vendaSemCicloVendedor")
    @Mapping(target = "vendedor", qualifiedByName = "vendedorApenasId")
    @Mapping(target = "veiculo", qualifiedByName = "veiculoApenasId")
    ClienteVenda vendaSemCicloVendedor(ClienteVendaEntity entity);

    @Named("concessionariaSemCiclo")
    @Mapping(target = "vendedor", qualifiedByName = "vendedorApenasId")
    @Mapping(target = "veiculo", qualifiedByName = "veiculoApenasId")
    Concessionaria concessionariaSemCiclo(ConcessionariaEntity entity);

    @Named("vendedorApenasId")
    @Mapping(target = "concessionaria", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    Vendedor vendedorApenasId(VendedorEntity entity);

    @Named("veiculoApenasId")
    @Mapping(target = "concessionaria", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    Veiculo veiculoApenasId(VeiculoEntity entity);

    VendedorEntity toEntity(Vendedor vendedor);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "ultimaAtualizacao", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    VendedorEntity toRequest(VendedorRequest vendedorRequest);

    @Mapping(target = "idConcessionaria", source = "concessionaria.id")
    @Mapping(target = "vendas", qualifiedByName = "vendaParaResponse")
    VendedorResponse toResponse(Vendedor vendedor);

    @Named("vendaParaResponse")
    @Mapping(target = "idVeiculo", source = "veiculo.id")
    @Mapping(target = "idVendedor", source = "vendedor.id")
    ClienteVendaResponse vendaParaResponse(ClienteVenda clienteVenda);

}
