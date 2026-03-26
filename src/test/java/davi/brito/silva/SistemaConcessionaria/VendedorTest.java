package davi.brito.silva.SistemaConcessionaria;

import davi.brito.silva.SistemaConcessionaria.core.gateway.VendedorGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Vendedor;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.atualizar.AtualizarVendedorUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.buscarPorId.BuscarVendedorPorIdUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.criar.CriarVendedorUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.remover.RemoverVendedorUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VendedorTest {

    @Captor
    private ArgumentCaptor<Vendedor> captorVendedor;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @InjectMocks
    private CriarVendedorUseCaseImpl criarVendedorUseCase;

    @InjectMocks
    private BuscarVendedorPorIdUseCaseImpl buscarVendedorPorIdUseCase;

    @InjectMocks
    private AtualizarVendedorUseCaseImpl atualizarVendedorUseCase;

    @InjectMocks
    private RemoverVendedorUseCaseImpl removerVendedorUseCase;

    @Mock
    private VendedorGateway gateway;

    @Nested
    class CriarVendedorUseCaseImplTest {

        @Test
        @DisplayName("Deve criar um vendedor com sucesso")
        void deveCriarUmVendedorComSucesso() {

            // Arrange

            var vendedor = new Vendedor(
                    UUID.randomUUID(),
                    "Davi Brito",
                    "216543278909",
                    25,
                    "11986543212",
                    45,
                    null,
                    null,
                    LocalDate.now(),
                    LocalDateTime.now()
                    );

            when(gateway.criarVendedor(vendedor)).thenReturn(vendedor);

            // Act

            var resultado = criarVendedorUseCase.execute(vendedor);

            // Assert

            assertNotNull(resultado);
            assertAll(
                    () -> assertEquals(vendedor.id(), resultado.id()),
                    () -> assertEquals(vendedor.nome(), resultado.nome()),
                    () -> assertEquals(vendedor.cpf(), resultado.cpf()),
                    () -> assertEquals(vendedor.idade(), resultado.idade()),
                    () -> assertEquals(vendedor.telefone(), resultado.telefone()),
                    () -> assertEquals(vendedor.porcentagemComissao(), resultado.porcentagemComissao()),
                    () -> assertEquals(vendedor.dataCadastro(), resultado.dataCadastro()));

            verify(gateway, times(1)).criarVendedor(vendedor);

        }

        @Test
        @DisplayName("Deve lançar uma exceção ao criar um vendedor com falha")
        void deveLancarUmaExcecaoAoCriarUmVendedorComFalha() {

            // Arrange

            var vendedor = new Vendedor(
                    UUID.randomUUID(),
                    "Davi Brito",
                    "216543278909",
                    25,
                    "11986543212",
                    45,
                    null,
                    null,
                    LocalDate.now(),
                    LocalDateTime.now()
            );

            doThrow(new RuntimeException("Erro ao criar vendedor")).when(gateway)
                    .criarVendedor(vendedor);

            // Act e Assert

            var exception = assertThrows(RuntimeException.class,
                    () -> criarVendedorUseCase.execute(vendedor));
            verify(gateway, times(1)).criarVendedor(vendedor);
            assertEquals("Erro ao criar vendedor", exception.getMessage());

        }

    }

    @Nested
    class BuscarVendedorPorIdUseCaseImplTest {

        @Test
        @DisplayName("Deve buscar um vendedor por id com sucesso")
        void deveBuscarUmVendedorPorIdComSucesso() {

            // Arrange

            var vendedor = new Vendedor(
                    UUID.randomUUID(),
                    "Davi Brito",
                    "216543278909",
                    25,
                    "11986543212",
                    45,
                    null,
                    null,
                    LocalDate.now(),
                    LocalDateTime.now()
            );

            when(gateway.buscarVendedorPorId(vendedor.id())).thenReturn(vendedor);

            // Act

            var buscaCorreta = buscarVendedorPorIdUseCase.execute(vendedor.id());

            // Assert

            assertNotNull(buscaCorreta);
            assertEquals(vendedor.id(), buscaCorreta.id());
            assertEquals(vendedor.cpf(), buscaCorreta.cpf());
            verify(gateway, times(1)).buscarVendedorPorId(vendedor.id());

        }

        @Test
        @DisplayName("Deve lançar uma exceção ao buscar um vendedor por id inexistente")
        void deveLancarExcecaoAoBuscarVendedorPorIdInexistente() {

            // Arrange

            var idInexistente = UUID.randomUUID();

            when(gateway.buscarVendedorPorId(idInexistente)).thenReturn(null);

            // Act e Assert

            var exception = assertThrows(RuntimeException.class,
                    () -> buscarVendedorPorIdUseCase.execute(idInexistente));
            verify(gateway, times(1)).buscarVendedorPorId(idInexistente);
            assertEquals("Vendedor com id (" + idInexistente + ") não encontrado", exception.getMessage());

        }

    }

    @Nested
    class AtualizarVendedorUseCaseImplTest {

        @Test
        @DisplayName("Deve atualizar um vendedor com sucesso")
        void deveAtualizarUmVendedorComSucesso() {

            // Arrange

            var vendedor = new Vendedor(
                    UUID.randomUUID(),
                    "Davi Brito",
                    "216543278909",
                    25,
                    "11986543212",
                    45,
                    null,
                    null,
                    LocalDate.now(),
                    LocalDateTime.now()
            );

            var vendedorAtualizado = new Vendedor(
                    vendedor.id(),
                    "Felipe Silva",
                    "444555278977",
                    35,
                    "11946503202",
                    40,
                    vendedor.concessionaria(),
                    vendedor.vendas(),
                    vendedor.dataCadastro(),
                    LocalDateTime.now());

            when(gateway.buscarVendedorPorId(captorUUID.capture())).thenReturn(vendedor);
            when(gateway.atualizarVendedor(captorVendedor.capture())).thenReturn(vendedorAtualizado);

            // Act

            var atualizar = atualizarVendedorUseCase.execute(vendedorAtualizado);

            // Assert

            assertNotNull(atualizar);
            assertEquals(vendedor.id(), atualizar.id());

            var capturar = captorVendedor.getValue();
            assertAll(
                    () -> assertEquals(vendedorAtualizado.id(), capturar.id()),
                    () -> assertEquals(vendedorAtualizado.nome(), capturar.nome()),
                    () -> assertEquals(vendedorAtualizado.cpf(), capturar.cpf()),
                    () -> assertEquals(vendedorAtualizado.idade(), capturar.idade()),
                    () -> assertEquals(vendedorAtualizado.telefone(), capturar.telefone()),
                    () -> assertEquals(vendedorAtualizado.porcentagemComissao(), capturar.porcentagemComissao()),
                    () -> assertEquals(vendedorAtualizado.dataCadastro(), capturar.dataCadastro()));
            verify(gateway, times(1)).buscarVendedorPorId(captorUUID.getValue());
            verify(gateway, times(1)).atualizarVendedor(capturar);
        }

        @Test
        @DisplayName("Deve lançar uma exceção ao atualizar um vendedor com id inexistente")
        void deveLancarUmaExcecaoAoAtualizarUmVendedorComIdInexistente() {

            // Arrange

            var idInexistente = UUID.randomUUID();

            var vendedorAtualizado = new Vendedor(
                    idInexistente,
                    "Felipe Silva",
                    "444555278977",
                    35,
                    "11946503202",
                    40,
                    null,
                    null,
                    LocalDate.now(),
                    LocalDateTime.now());

            when(gateway.buscarVendedorPorId(captorUUID.capture())).thenReturn(null);

            // Act e Assert

            var atualizarException = assertThrows(RuntimeException.class,
                    () -> atualizarVendedorUseCase.execute(vendedorAtualizado));

            assertEquals("Vendedor com id (" + captorUUID.getValue() + ") não encontrado", atualizarException.getMessage());
            verify(gateway, times(1)).buscarVendedorPorId(captorUUID.getValue());
            verify(gateway, times(0)).atualizarVendedor(any());
        }
    }

    @Nested
    class RemoverVendedorUseCaseImplTest {

        @Test
        @DisplayName("Deve remover um vendedor com sucesso")
        void deveRemoverUmVendedorComSucesso() {

            var vendedor = new Vendedor(
                    UUID.randomUUID(),
                    "Davi Brito",
                    "216543278909",
                    25,
                    "11986543212",
                    45,
                    null,
                    null,
                    LocalDate.now(),
                    LocalDateTime.now()
            );

            when(gateway.buscarVendedorPorId(captorUUID.capture())).thenReturn(vendedor);
            when(gateway.removerVendedor(captorVendedor.capture())).thenReturn(vendedor);

            // Act

            removerVendedorUseCase.execute(vendedor);

            // Assert

            assertEquals(vendedor.id(), captorUUID.getValue());
            verify(gateway, times(1)).buscarVendedorPorId(captorUUID.getValue());
            verify(gateway, times(1)).removerVendedor(captorVendedor.getValue());
        }

        @Test
        @DisplayName("Deve lançar uma exceção ao remover um vendedor com id inexistente")
        void deveLancarUmaExcecaoAoRemoverUmVendedorComIdInexistente() {

            var vendedor = new Vendedor(
                    UUID.randomUUID(),
                    "Davi Brito",
                    "216543278909",
                    25,
                    "11986543212",
                    45,
                    null,
                    null,
                    LocalDate.now(),
                    LocalDateTime.now()
            );

            when(gateway.buscarVendedorPorId(captorUUID.capture())).thenReturn(null);

            // Act

            var exception = assertThrows(RuntimeException.class,
                    () -> removerVendedorUseCase.execute(vendedor));

            // Assert

            assertEquals(vendedor.id(), captorUUID.getValue());
            assertEquals("Vendedor com id (" + captorUUID.getValue() + ") não encontrado", exception.getMessage());
            verify(gateway, times(1)).buscarVendedorPorId(captorUUID.getValue());
            verify(gateway, times(0)).removerVendedor(any());
        }

    }

}
