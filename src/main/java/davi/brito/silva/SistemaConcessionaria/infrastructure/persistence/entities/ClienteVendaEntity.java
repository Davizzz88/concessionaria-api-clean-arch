package davi.brito.silva.SistemaConcessionaria.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteVendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_venda")
    private UUID id;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "cpf_cliente", nullable = false)
    private String cpfCliente;

    @Column(name = "telefone_cliente")
    private String tefefoneCliente;

    @OneToOne
    @JoinColumn(name = "id_veiculo")
    private VeiculoEntity veiculo;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private VendedorEntity vendedor;

    @Column(name = "tipo_pagamento", nullable = false)
    private String tipoPagamento;

    @Column(name = "data_venda", nullable = false)
    private LocalDateTime dataVenda = LocalDateTime.now();

}
