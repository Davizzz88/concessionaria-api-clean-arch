package davi.brito.silva.SistemaConcessionaria.infrastructure.mapper;

import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;

import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.clienteVenda.ClienteVendaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaAtualizarRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaRequest;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.concessionaria.ConcessionariaResponse;
import davi.brito.silva.SistemaConcessionaria.infrastructure.dto.veiculo.VeiculoResponse;
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
public interface ConcessionariaMapper {

    @Mapping(target = "vendedor", qualifiedByName = "vendedorSemCiclo")
    @Mapping(target = "veiculo", qualifiedByName = "veiculoSemCiclo")
    Concessionaria toDomain(ConcessionariaEntity entity);

    @Named("vendedorSemCiclo")
    @Mapping(target = "concessionaria", qualifiedByName = "concessionariaApenasId")  // ← manter o ID!
    @Mapping(target = "vendas", qualifiedByName  = "vendaSemCiclo")
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

    @Named("vendaSemCiclo")
    @Mapping(target = "vendedor", qualifiedByName = "vendedorApenasId")
    @Mapping(target = "veiculo", qualifiedByName = "veiculoApenasId")
    ClienteVenda vendaSemCiclo(ClienteVendaEntity entity);

    @Named("vendedorApenasId")
    @Mapping(target = "concessionaria", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    Vendedor vendedorApenasId(VendedorEntity entity);

    @Named("veiculoApenasId")
    @Mapping(target = "concessionaria", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    Veiculo veiculoApenasId(VeiculoEntity entity);

    @Mapping(target = "authorities", ignore = true)
    ConcessionariaEntity toEntity(Concessionaria concessionaria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ultimaAtualizacao", ignore = true)
    @Mapping(target = "vendedor", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    ConcessionariaEntity toRequest(ConcessionariaRequest concessionariaRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ultimaAtualizacao", ignore = true)
    @Mapping(target = "vendedor", ignore = true)
    @Mapping(target = "veiculo", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "senha", ignore = true)
    ConcessionariaEntity toAtualizarRequest(ConcessionariaAtualizarRequest concessionariaAtualizarRequest);

    @Mapping(target = "vendedor", qualifiedByName = "vendedorParaResponse")
    @Mapping(target = "veiculo", qualifiedByName = "veiculoParaResponse")
    ConcessionariaResponse toResponse(Concessionaria concessionaria);

    @Named("vendedorParaResponse")
    @Mapping(target = "idConcessionaria", source = "concessionaria.id")
    @Mapping(target = "vendas", qualifiedByName = "vendaParaResponse")
    VendedorResponse vendedorParaResponse(Vendedor vendedor);

    @Named("veiculoParaResponse")
    @Mapping(target = "idConcessionaria", source = "concessionaria.id")
    VeiculoResponse veiculoParaResponse(Veiculo veiculo);

    @Named("vendaParaResponse")
    @Mapping(target = "idVeiculo", source = "veiculo.id")
    @Mapping(target = "idVendedor", source = "vendedor.id")
    ClienteVendaResponse vendaParaResponse(ClienteVenda clienteVenda);

}
