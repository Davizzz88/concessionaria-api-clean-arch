package davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.repository;

import davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities.ConcessionariaEntity;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface ConcessionariaRepository extends JpaRepository<ConcessionariaEntity, UUID> {

    @Transactional
    void deleteByIdAndNome(UUID id, String nomeConcessionaria);

    UserDetails findByNome(String nome);

}
