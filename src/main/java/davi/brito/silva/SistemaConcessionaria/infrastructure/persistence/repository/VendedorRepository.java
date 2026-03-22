package davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VendedorEntity;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendedorRepository extends JpaRepository<VendedorEntity, UUID> {

    @Transactional
    void deleteByIdAndCpf(UUID id, String cpfVendedor);

}
