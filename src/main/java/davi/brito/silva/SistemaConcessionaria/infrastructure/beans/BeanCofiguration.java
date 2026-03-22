package davi.brito.silva.SistemaConcessionaria.infrastructure.beans;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar.AtualizarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar.AtualizarConcessionariaUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId.BuscarConcessionatiaPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId.BuscarConcessionatiaPorIdUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.criar.CriarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.criar.CriarConcessionariaUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.remover.RemoverConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.remover.RemoverConcessionariaUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.atualizar.AtualizarVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.atualizar.AtualizarVeiculoUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.buscarPorId.BuscarVeiculoPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.buscarPorId.BuscarVeiculoPorIdUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.criar.CriarVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.criar.CriarVeiculoUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.remover.RemoverVeiculoUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.veiculo.remover.RemoverVeiculoUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.buscarPorId.BuscarClienteVendaPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.buscarPorId.BuscarClienteVendaPorIdUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.criar.CriarClienteVendaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendas.criar.CriarClienteVendaUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.atualizar.AtualizarVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.atualizar.AtualizarVendedorUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.buscarPorId.BuscarVendedorPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.buscarPorId.BuscarVendedorPorIdUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.criar.CriarVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.criar.CriarVendedorUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.remover.RemoverVendedorUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.vendedor.remover.RemoverVendedorUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanCofiguration {

    @Bean
    public CriarConcessionariaUseCase criarConcessionariaUseCase(ConcessionariaGateway gateway) {
        return new CriarConcessionariaUseCaseImpl(gateway);
    }

    @Bean
    public AtualizarConcessionariaUseCase atualizarConcessionariaUseCase(ConcessionariaGateway gateway) {
        return new AtualizarConcessionariaUseCaseImpl(gateway);
    }

    @Bean
    public RemoverConcessionariaUseCase removerConcessionariaUseCase(ConcessionariaGateway gateway) {
        return new RemoverConcessionariaUseCaseImpl(gateway);
    }

    @Bean
    public BuscarConcessionatiaPorIdUseCase buscarConcessionariaPorIdUseCase(ConcessionariaGateway gateway) {
        return new BuscarConcessionatiaPorIdUseCaseImpl(gateway);
    }

    @Bean
    public CriarVendedorUseCase criarVendedorUseCase(ConcessionariaGateway gateway) {
        return new CriarVendedorUseCaseImpl(gateway);
    }

    @Bean
    public AtualizarVendedorUseCase atualizarVendedorUseCase(ConcessionariaGateway gateway) {
        return new AtualizarVendedorUseCaseImpl(gateway);
    }

    @Bean
    public RemoverVendedorUseCase removerVendedorUseCase(ConcessionariaGateway gateway) {
        return new RemoverVendedorUseCaseImpl(gateway);
    }

    @Bean
    public BuscarVendedorPorIdUseCase buscarVendedorPorIdUseCase(ConcessionariaGateway gateway) {
        return new BuscarVendedorPorIdUseCaseImpl(gateway);
    }

    @Bean
    public CriarVeiculoUseCase criarVeiculoUseCase(ConcessionariaGateway gateway) {
        return new CriarVeiculoUseCaseImpl(gateway);
    }


    @Bean
    public AtualizarVeiculoUseCase atualizarVeiculoUseCase(ConcessionariaGateway gateway) {
        return new AtualizarVeiculoUseCaseImpl(gateway);

    }

    @Bean
    public RemoverVeiculoUseCase removerVeiculoUseCase(ConcessionariaGateway gateway) {
        return new RemoverVeiculoUseCaseImpl(gateway);
    }

    @Bean
    public BuscarVeiculoPorIdUseCase buscarVeiculoPorIdUseCase(ConcessionariaGateway gateway) {
        return new BuscarVeiculoPorIdUseCaseImpl(gateway);

    }

    @Bean
    public CriarClienteVendaUseCase criarClienteVendaUseCase(ConcessionariaGateway gateway) {
        return new CriarClienteVendaUseCaseImpl(gateway);

    }

    @Bean
    public BuscarClienteVendaPorIdUseCase buscarClienteVendaPorIdUseCase(ConcessionariaGateway gateway) {
        return new BuscarClienteVendaPorIdUseCaseImpl(gateway);
    }

}
