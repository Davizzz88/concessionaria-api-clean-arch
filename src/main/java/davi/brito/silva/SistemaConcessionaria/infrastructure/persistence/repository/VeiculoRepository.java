package davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.VeiculoEntity;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, UUID> {

    @Transactional
    void deleteByIdAndNome(UUID id, String nomeVeiculo);

}
