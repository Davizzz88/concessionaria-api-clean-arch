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
@Table(name = "tb_vendedor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendedorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_vendedor")
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "porcentagem_comissao", nullable = false)
    private Integer porcentagemComissao;

    @ManyToOne
    @JoinColumn(name = "id_concessionaria")
    private ConcessionariaEntity concessionaria;

    @OneToMany(mappedBy = "vendedor")
    private List<ClienteVendaEntity> vendas;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultimaAtualizacao = LocalDateTime.now();

}