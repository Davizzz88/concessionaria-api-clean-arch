package davi.brito.silva.SistemaConcessionaria.infrastructure.beans;

import davi.brito.silva.SistemaConcessionaria.core.gateway.ClienteVendaGateway;
import davi.brito.silva.SistemaConcessionaria.core.gateway.ConcessionariaGateway;
import davi.brito.silva.SistemaConcessionaria.core.gateway.VeiculoGateway;
import davi.brito.silva.SistemaConcessionaria.core.gateway.VendedorGateway;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar.AtualizarConcessionariaUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.atualizar.AtualizarConcessionariaUseCaseImpl;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId.BuscarConcessionariaPorIdUseCase;
import davi.brito.silva.SistemaConcessionaria.core.usecases.concessionaria.buscarPorId.BuscarConcessionariaPorIdUseCaseImpl;
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
public class BeanConfiguration {

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
    public BuscarConcessionariaPorIdUseCase buscarConcessionariaPorIdUseCase(ConcessionariaGateway gateway) {
        return new BuscarConcessionariaPorIdUseCaseImpl(gateway);
    }

    @Bean
    public CriarVendedorUseCase criarVendedorUseCase(VendedorGateway gateway) {
        return new CriarVendedorUseCaseImpl(gateway);
    }

    @Bean
    public AtualizarVendedorUseCase atualizarVendedorUseCase(VendedorGateway gateway) {
        return new AtualizarVendedorUseCaseImpl(gateway);
    }

    @Bean
    public RemoverVendedorUseCase removerVendedorUseCase(VendedorGateway gateway) {
        return new RemoverVendedorUseCaseImpl(gateway);
    }

    @Bean
    public BuscarVendedorPorIdUseCase buscarVendedorPorIdUseCase(VendedorGateway gateway) {
        return new BuscarVendedorPorIdUseCaseImpl(gateway);
    }

    @Bean
    public CriarVeiculoUseCase criarVeiculoUseCase(VeiculoGateway gateway) {
        return new CriarVeiculoUseCaseImpl(gateway);
    }


    @Bean
    public AtualizarVeiculoUseCase atualizarVeiculoUseCase(VeiculoGateway gateway) {
        return new AtualizarVeiculoUseCaseImpl(gateway);

    }

    @Bean
    public RemoverVeiculoUseCase removerVeiculoUseCase(VeiculoGateway gateway) {
        return new RemoverVeiculoUseCaseImpl(gateway);
    }

    @Bean
    public BuscarVeiculoPorIdUseCase buscarVeiculoPorIdUseCase(VeiculoGateway gateway) {
        return new BuscarVeiculoPorIdUseCaseImpl(gateway);

    }

    @Bean
    public CriarClienteVendaUseCase criarClienteVendaUseCase(ClienteVendaGateway gateway) {
        return new CriarClienteVendaUseCaseImpl(gateway);

    }

    @Bean
    public BuscarClienteVendaPorIdUseCase buscarClienteVendaPorIdUseCase(ClienteVendaGateway gateway) {
        return new BuscarClienteVendaPorIdUseCaseImpl(gateway);
    }

}
