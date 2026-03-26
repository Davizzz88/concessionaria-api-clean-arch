package davi.brito.silva.SistemaConcessionaria;

import davi.brito.silva.SistemaConcessionaria.core.enums.StatusVeiculo;
import davi.brito.silva.SistemaConcessionaria.core.gateway.ClienteVendaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.ClienteVenda;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.buscarPorId.BuscarClienteVendaPorIdUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.criar.CriarClienteVendaUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteVendaTest {

    @InjectMocks
    private CriarClienteVendaUseCaseImpl criarClienteVendaUseCase;

    @InjectMocks
    private BuscarClienteVendaPorIdUseCaseImpl buscarClienteVendaPorIdUseCase;

    @Mock
    private ClienteVendaGateway gateway;

    @Nested
    class CriarClienteVendaUseCaseImplTest {

        @Test
        @DisplayName("Deve criar uma venda com sucesso")
        void deveCriarUmClienteVendaComSucesso() {

            // Arrange

            var veiculo = new Veiculo(
                    UUID.randomUUID(),
                    "Carro",
                    "Cayene",
                    "Porsche",
                    2026,
                    800000.0,
                    null,
                    StatusVeiculo.DISPONIVEL,
                    null,
                    LocalDateTime.now(),
                    LocalDateTime.now()

            );

            var clienteVenda = new ClienteVenda(

                    UUID.randomUUID(),
                    "João Silva",
                    "31061415409",
                    "11987654321",
                    veiculo,
                    null,
                    "PIX",
                    LocalDateTime.now()
            );

            when(gateway.criarClienteVenda(clienteVenda, veiculo.id())).thenReturn(clienteVenda);

            // Act

            var resultado = criarClienteVendaUseCase.execute(clienteVenda, veiculo.id());

            // Assert

            assertNotNull(resultado);
            assertAll(
                    () -> assertEquals(clienteVenda.id(), resultado.id()),
                    () -> assertEquals(clienteVenda.nomeCliente(), resultado.nomeCliente()),
                    () -> assertEquals(clienteVenda.cpfCliente(), resultado.cpfCliente()),
                    () -> assertEquals(clienteVenda.telefoneCliente(), resultado.telefoneCliente()),
                    () -> assertEquals(clienteVenda.veiculo(), resultado.veiculo()),
                    () -> assertEquals(clienteVenda.dataVenda(), resultado.dataVenda()));

            verify(gateway, times(1)).criarClienteVenda(clienteVenda, veiculo.id());

        }

        @Test
        @DisplayName("Deve lançar uma exceção ao criar uma venda com falha")
        void deveLancarUmaExcecaoAoCriarUmClienteVendaComFalha() {

            // Arrange

            var veiculo = new Veiculo(
                    UUID.randomUUID(),
                    "Carro",
                    "Cayene",
                    "Porsche",
                    2026,
                    800000.0,
                    null,
                    StatusVeiculo.DISPONIVEL,
                    null,
                    LocalDateTime.now(),
                    LocalDateTime.now()

            );

            var clienteVenda = new ClienteVenda(

                    UUID.randomUUID(),
                    "João Silva",
                    "31061415409",
                    "11987654321",
                    veiculo,
                    null,
                    "PIX",
                    LocalDateTime.now()
            );

            doThrow(new RuntimeException("Erro ao criar venda")).when(gateway)
                    .criarClienteVenda(clienteVenda, veiculo.id());

            // Act e Assert

            var exception = assertThrows(RuntimeException.class,
                    () -> criarClienteVendaUseCase.execute(clienteVenda, veiculo.id()));
            verify(gateway, times(1)).criarClienteVenda(clienteVenda, veiculo.id());
            assertEquals("Erro ao criar venda", exception.getMessage());

        }

    }

    @Nested
    class BuscarClienteVendaPorIdUseCaseImplTest {

        @Test
        @DisplayName("Deve buscar uma venda por id com sucesso")
        void deveBuscarUmClienteVendaPorIdComSucesso() {

            // Arrange

            var veiculo = new Veiculo(
                    UUID.randomUUID(),
                    "Carro",
                    "Cayene",
                    "Porsche",
                    2026,
                    800000.0,
                    null,
                    StatusVeiculo.DISPONIVEL,
                    null,
                    LocalDateTime.now(),
                    LocalDateTime.now()

            );

            var clienteVenda = new ClienteVenda(

                    UUID.randomUUID(),
                    "João Silva",
                    "31061415409",
                    "11987654321",
                    veiculo,
                    null,
                    "PIX",
                    LocalDateTime.now()
            );

            when(gateway.buscarVendaPorId(clienteVenda.id())).thenReturn(clienteVenda);

            // Act

            var buscaCorreta = buscarClienteVendaPorIdUseCase.execute(clienteVenda.id());

            // Assert

            assertNotNull(buscaCorreta);

            assertAll(
                    () -> assertEquals(clienteVenda.id(), buscaCorreta.id()),
                    () -> assertEquals(clienteVenda.nomeCliente(), buscaCorreta.nomeCliente()),
                    () -> assertEquals(clienteVenda.cpfCliente(), buscaCorreta.cpfCliente()),
                    () -> assertEquals(clienteVenda.veiculo(), buscaCorreta.veiculo())
            );
            verify(gateway, times(1)).buscarVendaPorId(clienteVenda.id());

        }

        @Test
        @DisplayName("Deve lançar uma exceção ao buscar uma venda por id inexistente")
        void deveLancarExcecaoAoBuscarUmClienteVendaPorIdInexistente() {

            // Arrange

            var idInexistente = UUID.randomUUID();

            when(gateway.buscarVendaPorId(idInexistente)).thenReturn(null);

            // Act e Assert

            var exception = assertThrows(RuntimeException.class,
                    () -> buscarClienteVendaPorIdUseCase.execute(idInexistente));
            verify(gateway, times(1)).buscarVendaPorId(idInexistente);
            assertEquals("Venda com id (" + idInexistente + ") não encontrada", exception.getMessage());

        }

    }

}
