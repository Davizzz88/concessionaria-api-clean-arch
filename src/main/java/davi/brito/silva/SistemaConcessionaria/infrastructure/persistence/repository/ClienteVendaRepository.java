package davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ClienteVendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteVendaRepository extends JpaRepository<ClienteVendaEntity, UUID> {
}
