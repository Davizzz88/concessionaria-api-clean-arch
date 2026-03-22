package davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_concessionaria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConcessionariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_concessionaria")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "telefone")
    private String telefone;

    @OneToMany(mappedBy = "concessionaria")
    private List<VendedorEntity> vendedor;

    @OneToMany(mappedBy = "concessionaria")
    private List<VeiculoEntity> veiculo;

    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultimaAtualizacao = LocalDateTime.now();

}
