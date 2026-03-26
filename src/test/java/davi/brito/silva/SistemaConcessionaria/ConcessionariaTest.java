package davi.brito.silva.SistemaConcessionaria;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Concessionaria;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar.AtualizarConcessionariaUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId.BuscarConcessionariaPorIdUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.criar.CriarConcessionariaUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.remover.RemoverConcessionariaUseCaseImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ConcessionariaTest {

    @Captor
    private ArgumentCaptor<Concessionaria> captorConcessionaria;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @InjectMocks
    private CriarConcessionariaUseCaseImpl criarConcessionariaUseCase;

    @InjectMocks
    private BuscarConcessionariaPorIdUseCaseImpl buscarConcessionatiaPorIdUseCase;

    @InjectMocks
    private AtualizarConcessionariaUseCaseImpl atualizarConcessionariaUseCase;

    @InjectMocks
    private RemoverConcessionariaUseCaseImpl removerConcessionariaUseCase;

    @Mock
    private ConcessionariaGateway gateway;

    @Nested
    class CriarConcessionariaUseCaseImplTest {

        @Test
        @DisplayName("Deve criar uma concessionária com sucesso")
        void deveCriarUmaConcessionariaComSucesso() {

            // Arrange

            var concessionaria = new Concessionaria(
                    UUID.randomUUID(),
                    "Concessionária ABC",
                    "12.345.678/0001-90",
                    "SP",
                    "São Paulo",
                    "(11) 1234-5678",
                    null,
                    null,
                    LocalDateTime.now()
            );

            when(gateway.criarConcessionaria(concessionaria)).thenReturn(concessionaria);

            // Act

            var resultado = criarConcessionariaUseCase.execute(concessionaria);

            // Assert

            assertNotNull(resultado);
            assertAll(
                    () -> assertEquals(concessionaria.id(), resultado.id()),
                    () -> assertEquals(concessionaria.nome(), resultado.nome()),
                    () -> assertEquals(concessionaria.cnpj(), resultado.cnpj()),
                    () -> assertEquals(concessionaria.estado(), resultado.estado()),
                    () -> assertEquals(concessionaria.cidade(), resultado.cidade()),
                    () -> assertEquals(concessionaria.telefone(), resultado.telefone())
            );

            verify(gateway, times(1)).criarConcessionaria(concessionaria);

        }

        @Test
        @DisplayName("Deve lançar uma exceção ao criar uma concessionária com falha")
        void deveLancarUmaExcecaoAoCriarUmaConcessionariaComFalha() {

            // Arrange

            var concessionaria = new Concessionaria(
                    UUID.randomUUID(),
                    "Concessionária ABC",
                    "12.345.678/0001-90",
                    "SP",
                    "São Paulo",
                    "(11) 1234-5678",
                    null,
                    null,
                    LocalDateTime.now()
            );

            doThrow(new RuntimeException("Erro ao criar concessionária")).when(gateway).criarConcessionaria(concessionaria);

            // Act e Assert

            var exception = assertThrows(RuntimeException.class, () -> criarConcessionariaUseCase.execute(concessionaria));
            verify(gateway, times(1)).criarConcessionaria(concessionaria);
            assertEquals("Erro ao criar concessionária", exception.getMessage());

        }

    }

    @Nested
    class BuscarConcessionariaPorIdUseCaseImplTest {

        @Test
        @DisplayName("Deve buscar uma concessionária por id com sucesso")
        void deveBuscarUmaConcessionariaPorIdComSucesso() {

            // Arrange

            var concessionaria = new Concessionaria(
                    UUID.randomUUID(),
                    "Concessionária ABC",
                    "12.345.678/0001-90",
                    "SP",
                    "São Paulo",
                    "(11) 1234-5678",
                    null,
                    null,
                    LocalDateTime.now()
            );

            when(gateway.buscarConcessionariaPorId(concessionaria.id())).thenReturn(concessionaria);

            // Act

            var buscaCorreta = buscarConcessionatiaPorIdUseCase.execute(concessionaria.id());

            // Assert

            assertNotNull(buscaCorreta);
            assertEquals(concessionaria.id(), buscaCorreta.id());
            assertEquals(concessionaria.cnpj(), buscaCorreta.cnpj());
            verify(gateway, times(1)).buscarConcessionariaPorId(concessionaria.id());

        }

        @Test
        @DisplayName("Deve lançar uma exceção ao buscar uma concessionária por id inexistente")
        void deveLancarExcecaoAoBuscarConcessionariaInexistente() {

            // Arrange

            var idInexistente = UUID.randomUUID();

            when(gateway.buscarConcessionariaPorId(idInexistente)).thenReturn(null);

            // Act e Assert

            var exception = assertThrows(RuntimeException.class, () -> buscarConcessionatiaPorIdUseCase.execute(idInexistente));
            verify(gateway, times(1)).buscarConcessionariaPorId(idInexistente);
            assertEquals("Concessionaria com id (" + idInexistente + ") não encontrada", exception.getMessage());

        }

    }

    @Nested
    class AtualizarConcessionariaUseCaseImplTest {

        @Test
        @DisplayName("Deve atualizar uma concessionária com sucesso")
        void deveAtualizarUmaConcessionariaComSucesso() {

            // Arrange

            var concessionaria = new Concessionaria(
                    UUID.randomUUID(),
                    "Concessionária ABC",
                    "12.345.678/0001-90",
                    "SP",
                    "São Paulo",
                    "(11) 1234-5678",
                    null,
                    null,
                    LocalDateTime.now()
            );

            var concessionariaAtualizada = new Concessionaria(
                    concessionaria.id(),
                    "Concessionária XYZ",
                    "98.765.432/0001-09",
                    "RJ",
                    "Rio de Janeiro",
                    "(21) 9876-5432",
                    concessionaria.vendedor(),
                    concessionaria.veiculo(),
                    LocalDateTime.now()
            );

            when(gateway.buscarConcessionariaPorId(concessionaria.id())).thenReturn(concessionaria);
            when(gateway.atualizarConcessionaria(captorConcessionaria.capture())).thenReturn(concessionariaAtualizada);

            // Act

            var atualizar = atualizarConcessionariaUseCase.execute(concessionariaAtualizada);

            // Assert

            assertNotNull(atualizar);
            assertEquals(concessionaria.id(), atualizar.id());

            var capturar = captorConcessionaria.getValue();
            verify(gateway, times(1)).buscarConcessionariaPorId(captorUUID.capture());
            verify(gateway, times(1)).atualizarConcessionaria(capturar);
            assertAll(
                    () -> assertEquals(concessionariaAtualizada.id(), capturar.id()),
                    () -> assertEquals(concessionariaAtualizada.nome(), capturar.nome()),
                    () -> assertEquals(concessionariaAtualizada.cnpj(), capturar.cnpj()),
                    () -> assertEquals(concessionariaAtualizada.estado(), capturar.estado()),
                    () -> assertEquals(concessionariaAtualizada.cidade(), capturar.cidade()),
                    () -> assertEquals(concessionariaAtualizada.telefone(), capturar.telefone())
            );
        }

        @Test
        @DisplayName("Deve lançar uma exceção ao atualizar uma concessionária com id inexistente")
        void deveLancarUmaExcecaoAoAtualizarUmaConcessionariaComIdInexistente() {
// Arrange

            var idInexistente = UUID.randomUUID();

            var concessionariaAtualizada = new Concessionaria(
                    idInexistente,
                    "Concessionária XYZ",
                    "98.765.432/0001-09",
                    "RJ",
                    "Rio de Janeiro",
                    "(21) 9876-5432",
                    null,
                    null,
                    LocalDateTime.now()
            );

            when(gateway.buscarConcessionariaPorId(captorUUID.capture())).thenReturn(null);

            // Act e Assert

            var atualizarException = assertThrows(RuntimeException.class,
                    () -> atualizarConcessionariaUseCase.execute(concessionariaAtualizada));

            assertEquals("Concessionaria com id (" + captorUUID.getValue() + ") não encontrada", atualizarException.getMessage());
            verify(gateway, times(1)).buscarConcessionariaPorId(captorUUID.getValue());
            verify(gateway, times(0)).atualizarConcessionaria(any());

        }

    }

    @Nested
    class RemoverConcessionariaUseCaseImplTest {

        @Test
        @DisplayName("Deve remover uma concessionária com sucesso")
        void deveRemoverUmaConcessionariaComSucesso() {

            // Arrange

            var concessionaria = new Concessionaria(
                    UUID.randomUUID(),
                    "Concessionária ABC",
                    "12.345.678/0001-90",
                    "SP",
                    "São Paulo",
                    "(11) 1234-5678",
                    null,
                    null,
                    LocalDateTime.now()
            );

            when(gateway.buscarConcessionariaPorId(captorUUID.capture())).thenReturn(concessionaria);
            when(gateway.removerConcessionaria(captorConcessionaria.capture())).thenReturn(concessionaria);

            // Act

            removerConcessionariaUseCase.execute(concessionaria);

            // Assert

            assertEquals(concessionaria.id(), captorUUID.getValue());
            verify(gateway, times(1)).buscarConcessionariaPorId(captorUUID.getValue());
            verify(gateway, times(1)).removerConcessionaria(captorConcessionaria.getValue());
        }

        @Test
        @DisplayName("Deve lançar uma exceção ao remover uma concessionária com id inexistente")
        void deveLancarUmaExcecaoAoRemoverUmaConcessionariaComIdInexistente() {

            var concessionaria = new Concessionaria(
                    UUID.randomUUID(),
                    "Concessionária ABC",
                    "12.345.678/0001-90",
                    "SP",
                    "São Paulo",
                    "(11) 1234-5678",
                    null,
                    null,
                    LocalDateTime.now()
            );

            when(gateway.buscarConcessionariaPorId(captorUUID.capture())).thenReturn(null);

            // Act

            var exception = assertThrows(RuntimeException.class,
                    () -> removerConcessionariaUseCase.execute(concessionaria));

            // Assert

            assertEquals(concessionaria.id(), captorUUID.getValue());
            assertEquals("Concessionaria com id (" + captorUUID.getValue() + ") não encontrada", exception.getMessage());
            verify(gateway, times(1)).buscarConcessionariaPorId(captorUUID.getValue());
            verify(gateway, times(0)).removerConcessionaria(any());

        }
    }
}
