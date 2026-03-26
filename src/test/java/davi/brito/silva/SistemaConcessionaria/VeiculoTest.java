package davi.brito.silva.SistemaConcessionaria;

import davi.brito.silva.SistemaConcessionaria.core.enums.StatusVeiculo;
import davi.brito.silva.SistemaConcessionaria.core.gateway.VeiculoGateway;
import davi.brito.silva.SistemaConcessionaria.core.model.Veiculo;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.atualizar.AtualizarVeiculoUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.buscarPorId.BuscarVeiculoPorIdUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.criar.CriarVeiculoUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.remover.RemoverVeiculoUseCaseImpl;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoTest {

    @Captor
    private ArgumentCaptor<Veiculo> captorVeiculo;

    @Captor
    private ArgumentCaptor<UUID> captorUUID;

    @InjectMocks
    private CriarVeiculoUseCaseImpl criarVeiculoUseCase;

    @InjectMocks
    private BuscarVeiculoPorIdUseCaseImpl buscarVeiculoPorIdUseCase;

    @InjectMocks
    private AtualizarVeiculoUseCaseImpl atualizarVeiculoUseCase;

    @InjectMocks
    private RemoverVeiculoUseCaseImpl removerVeiculoUseCase;

    @Mock
    private VeiculoGateway gateway;

    @Nested
    class CriarVeiculoUseCaseImplTest {

        @Test
        @DisplayName("Deve criar um veiculo com sucesso")
        void deveCriarUmVeiculoComSucesso() {

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

            when(gateway.criarVeiculo(veiculo)).thenReturn(veiculo);

            // Act

            var resultado = criarVeiculoUseCase.execute(veiculo);

            // Assert

            assertNotNull(resultado);
            assertAll(
                    () -> assertEquals(veiculo.id(), resultado.id()),
                    () -> assertEquals(veiculo.nome(), resultado.nome()),
                    () -> assertEquals(veiculo.marca(), resultado.marca()),
                    () -> assertEquals(veiculo.ano(), resultado.ano()),
                    () -> assertEquals(veiculo.valor(), resultado.valor()),
                    () -> assertEquals(veiculo.statusVeiculo(), resultado.statusVeiculo()),
                    () -> assertEquals(veiculo.dataCadastro(), resultado.dataCadastro()));

            verify(gateway, times(1)).criarVeiculo(veiculo);

        }

        @Test
        @DisplayName("Deve lançar uma exceção ao criar um veiculo com falha")
        void deveLancarUmaExcecaoAoCriarUmVeiculoComFalha() {

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

            doThrow(new RuntimeException("Erro ao criar veiculo")).when(gateway)
                    .criarVeiculo(veiculo);

            // Act e Assert

            var exception = assertThrows(RuntimeException.class,
                    () -> criarVeiculoUseCase.execute(veiculo));
            verify(gateway, times(1)).criarVeiculo(veiculo);
            assertEquals("Erro ao criar veiculo", exception.getMessage());

        }

    }

    @Nested
    class BuscarVeiculoPorIdUseCaseImplTest {

        @Test
        @DisplayName("Deve buscar um veiculo por id com sucesso")
        void deveBuscarUmVeiculoPorIdComSucesso() {

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

            when(gateway.buscarVeiculoPorId(veiculo.id())).thenReturn(veiculo);

            // Act

            var buscaCorreta = buscarVeiculoPorIdUseCase.execute(veiculo.id());

            // Assert

            assertNotNull(buscaCorreta);
            assertEquals(veiculo.id(), buscaCorreta.id());
            assertEquals(veiculo.nome(), buscaCorreta.nome());
            verify(gateway, times(1)).buscarVeiculoPorId(veiculo.id());

        }

        @Test
        @DisplayName("Deve lançar uma exceção ao buscar um veiculo por id inexistente")
        void deveLancarExcecaoAoBuscarUmVeiculoPorIdInexistente() {

            // Arrange

            var idInexistente = UUID.randomUUID();

            when(gateway.buscarVeiculoPorId(idInexistente)).thenReturn(null);

            // Act e Assert

            var exception = assertThrows(RuntimeException.class,
                    () -> buscarVeiculoPorIdUseCase.execute(idInexistente));
            verify(gateway, times(1)).buscarVeiculoPorId(idInexistente);
            assertEquals("Veiculo com id (" + idInexistente + ") não encontrado", exception.getMessage());

        }

    }

    @Nested
    class AtualizarVeiculoUseCaseImplTest {

        @Test
        @DisplayName("Deve atualizar um veiculo com sucesso")
        void deveAtualizarUmVeiculoComSucesso() {

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

            var veiculoAtualizado = new Veiculo(
                    veiculo.id(),
                    "Carro",
                    "Toro",
                    "Fiat",
                    2024,
                    150000.0,
                    veiculo.concessionaria(),
                    veiculo.statusVeiculo(),
                    veiculo.vendas(),
                    veiculo.dataCadastro(),
                    LocalDateTime.now()

            );

            when(gateway.buscarVeiculoPorId(captorUUID.capture())).thenReturn(veiculo);
            when(gateway.atualizarVeiculo(captorVeiculo.capture())).thenReturn(veiculoAtualizado);

            // Act

            var atualizar = atualizarVeiculoUseCase.execute(veiculoAtualizado);

            // Assert

            assertNotNull(atualizar);
            assertEquals(veiculo.id(), atualizar.id());

            var capturar = captorVeiculo.getValue();
            assertAll(
                    () -> assertEquals(veiculoAtualizado.id(), capturar.id()),
                    () -> assertEquals(veiculoAtualizado.nome(), capturar.nome()),
                    () -> assertEquals(veiculoAtualizado.marca(), capturar.marca()),
                    () -> assertEquals(veiculoAtualizado.ano(), capturar.ano()),
                    () -> assertEquals(veiculoAtualizado.valor(), capturar.valor()),
                    () -> assertEquals(veiculoAtualizado.statusVeiculo(), capturar.statusVeiculo()),
                    () -> assertEquals(veiculoAtualizado.dataCadastro(), capturar.dataCadastro()));
            verify(gateway, times(1)).buscarVeiculoPorId(captorUUID.getValue());
            verify(gateway, times(1)).atualizarVeiculo(capturar);
        }

        @Test
        @DisplayName("Deve lançar uma exceção ao atualizar um veiculo com id inexistente")
        void deveLancarUmaExcecaoAoAtualizarUmVeiculoComIdInexistente() {

            // Arrange

            var idInexistente = UUID.randomUUID();

            var veiculoAtualizado = new Veiculo(
                    idInexistente,
                    "Carro",
                    "Toro",
                    "Fiat",
                    2024,
                    150000.0,
                    null,
                    StatusVeiculo.DISPONIVEL,
                    null,
                    null,
                    LocalDateTime.now()

            );

            when(gateway.buscarVeiculoPorId(captorUUID.capture())).thenReturn(null);

            // Act e Assert

            var atualizarException = assertThrows(RuntimeException.class,
                    () -> atualizarVeiculoUseCase.execute(veiculoAtualizado));

            assertEquals("Veiculo com id (" + captorUUID.getValue() + ") não encontrado", atualizarException.getMessage());
            verify(gateway, times(1)).buscarVeiculoPorId(captorUUID.getValue());
            verify(gateway, times(0)).atualizarVeiculo(any());
        }
    }

    @Nested
    class RemoverVeiculoUseCaseImplTest {

        @Test
        @DisplayName("Deve remover um veiculo com sucesso")
        void deveRemoverUmVeiculoComSucesso() {

            var veiculo = new Veiculo(
                    UUID.randomUUID(),
                    "Carro",
                    "Toro",
                    "Fiat",
                    2024,
                    150000.0,
                    null,
                    StatusVeiculo.DISPONIVEL,
                    null,
                    null,
                    LocalDateTime.now()

            );

            when(gateway.buscarVeiculoPorId(captorUUID.capture())).thenReturn(veiculo);
            when(gateway.removerVeiculo(captorVeiculo.capture())).thenReturn(veiculo);

            // Act

            removerVeiculoUseCase.execute(veiculo);

            // Assert

            assertEquals(veiculo.id(), captorUUID.getValue());
            verify(gateway, times(1)).buscarVeiculoPorId(captorUUID.getValue());
            verify(gateway, times(1)).removerVeiculo(captorVeiculo.getValue());
        }

        @Test
        @DisplayName("Deve lançar uma exceção ao remover um veiculo com id inexistente")
        void deveLancarUmaExcecaoAoRemoverUmVeiculoComIdInexistente() {

            var veiculo = new Veiculo(
                    UUID.randomUUID(),
                    "Carro",
                    "Toro",
                    "Fiat",
                    2024,
                    150000.0,
                    null,
                    StatusVeiculo.DISPONIVEL,
                    null,
                    null,
                    LocalDateTime.now()

            );

            when(gateway.buscarVeiculoPorId(captorUUID.capture())).thenReturn(null);

            // Act

            var exception = assertThrows(RuntimeException.class,
                    () -> removerVeiculoUseCase.execute(veiculo));

            // Assert

            assertEquals(veiculo.id(), captorUUID.getValue());
            assertEquals("Veiculo com id (" + captorUUID.getValue() + ") não encontrado", exception.getMessage());
            verify(gateway, times(1)).buscarVeiculoPorId(captorUUID.getValue());
            verify(gateway, times(0)).removerVeiculo(any());
        }

    }

}
