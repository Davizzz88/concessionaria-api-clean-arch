package davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities;

import davi.brito.silva.SistemaConcessionaria.core.enums.StatusVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_veiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_veiculo")
    private UUID id;

    @Column(name = "tipo_veiculo", nullable = false)
    private String tipoVeiculo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    @Column(name = "valor", nullable = false)
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_concessionaria")
    private ConcessionariaEntity concessionaria;

    @Column(name = "status_veiculo", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo statusVeiculo = StatusVeiculo.DISPONIVEL;

    @OneToOne(mappedBy = "veiculo")
    private ClienteVendaEntity vendas;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultimaAtualizacao = LocalDateTime.now();

}
