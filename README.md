# API Sistema de Gestão de Concessionária

Este projeto é uma aplicação **Spring Boot** autêntica, desenvolvida para gerenciar de forma profissional as operações de uma concessionária de veículos, abrangendo desde a gestão de estoque até o fluxo completo de vendas e comissionamento.

O foco principal deste desenvolvimento foi a aplicação de **Clean Architecture**, **SOLID** e padrões nativos de Design de Software, garantindo que o "Core" (Regras de Negócio) seja independente dos detalhes de infraestrutura (como banco de dados ou frameworks).

---

## 🏗️ Arquitetura do Sistema

A aplicação segue os princípios da **Arquitetura Limpa**, organizada em camadas bem definidas para separação de responsabilidades (SoC):

### 1. Core 
*   **Models:** Representam as entidades do mundo real (`Concessionaria`, `Veiculo`, `Vendedor`, `ClienteVenda`) usando **Records**, garantindo imutabilidade e clareza no transporte de dados.
*   **UseCases:** Cada ação do sistema é um caso de uso isolado (ex: `CriarClienteVendaUseCase`). Isso facilita a manutenção e evita "classes gigantes" que fazem tudo.
*   **Gateways (Interfaces):** São contratos de como o Core deve se comunicar com o mundo externo, sem saber se está usando um banco SQL, NoSQL ou uma API externa.
*   **Exception Hierarchy:** Hierarquia robusta de exceções de domínio (`DomainException` -> `NotFoundException` -> específicas), impedindo o vazamento de exceções infraestruturais (como SQL) nas regras de negócio.

### 2. Infraestrutura 
*   **Persistence:** Utilizei o **Spring Data JPA** com entidades Hibernate robustas, aplicando mapeamentos complexos de `OneToMany`, `ManyToOne` e bidirecionais.
*   **GatewayImpl:** Onde o contrato do Core é cumprido, realizando o acesso real aos dados.
*   **Controllers & RestAdvice:** Camada de entrega REST blindada por um **GlobalExceptionHandler** para tratamento universal e padronizado de códigos HTTP (400, 403, 404, 500).
*   **DTOs (Data Transfer Objects):** Separação inteligente de dados de entrada (Request/AtualizarRequest) e saída (Response). O banco de dados nunca é exposto diretamente à API.
*   **MapStruct:** Toda a conversão entre Domínio, Entidade e DTO é feita de forma performatica e escalável.

---

## Segurança: Autenticação JWT (Spring Security)

A aplicação é protegida por um sistema robusto de autenticação e autorização stateless (sem estado) baseado em Tokens JWT.

*   **Token JWT:** Geração e validação de tokens seguros com expiração de 2h controlada através do `TokenService`.
*   **Filtros de Segurança:** Implementação de `SecurityFilter` customizado que intercepta requisições HTTP, extrai o *Bearer Token*, valida a assinatura e injeta o contexto de segurança no Spring.
*   **Rotas Protegidas:** Configuração via `SecurityConfig`, onde rotas públicas (como `/login` e criação inicial da concessionária) são liberadas, mas operações gerenciais de Veiculo, Vendedore e ClienteVenda exigem autenticação ativa.
*   **Isolamento Sensível:** As senhas da Concessionária são criptografadas (BCrypt) e o sistema foi desenhado para **preservar a senha e a integridade do login** durante operações de atualização parcial (`PUT`), graças à criação do DTO específico de atualização (`ConcessionariaAtualizarRequest`).

---

## Desafio: Ciclos Infinitos com MapStruct

Um dos desafios técnicos mais profundos deste projeto foi resolver de forma definitiva as referências circulares bidirecionais (Ciclo Infinito) que causavam `StackOverflowError` no MapStruct.

**Problema:**
Em bancos estruturados JPA/Hibernate, a relação bidirecional é comum devido as anotações `@ManyToOne` e `@OneToMany`. A `Concessionaria` possui uma lista de `Vendedores`, os quais apontam de volta para a `Concessionaria`. O MapStruct, ao tentar converter as entidades em modelos de Domínio, navegava pelas relações de forma infinita (A -> B -> A -> B...), assim estourando a memória e bloqueando operações como GET, PUT e DELETE.

**Solução:**
A abordagem mais facil seria simplesmente colocar `ignore=true` ou tentar `@JsonIgnore`, mas isso removeria dados (como IDs no Response) e disfarçaria o problema real.

A solução adequada foi:
1.  **Mapeamentos @Named:** Criação de métodos isolados sem ciclos (`vendedorApenasId`, `veiculoSemCiclo`) para controlar exatamente o nível e a profundidade que o mapeador deve navegar nas camadas.
2.  **Preservação de IDs:** Em vez de ignorar as classes adjacentes por completo, implementei um capturador de ID. Dessa forma, as sub-listas DTO (ex: Vendedor retornando `idConcessionaria`) mantêm sua integridade funcional no JSON de saída sem disparar o loop.
3.  **Resultado:** Alta estabilidade, zero dependência `@JsonIgnoreProperties` e zero referências circulares bidirecionais.

---

## Testes Automatizados

Para garantir a robustez das regras de negócio e a facilidade de manutenção, implementei **Testes Unitários** de alta performance cobrindo todos os UseCases do sistema.

- **Estrutura AAA (Arrange, Act, Assert):** Testes organizados para máxima clareza e previsibilidade de resultados.
- **JUnit 5 & Mockito:** Utilização de Mocks para isolar a lógica de negócio de dependências da camada de infraestrutura.
- **ArgumentCaptor:** Técnica aplicada para capturar e validar os dados exatos que são transacionados entre as camadas.
- **Cobertura de Exceções:** Garantia de que o sistema reage corretamente a falhas, regras de negócio violadas e concorrência.

---

## Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot 3**
*   **Spring Security & JWT (Auth)**
*   **Spring Data JPA** e **H2 Database** (Memória)
*   **JUnit 5** e **Mockito** 
*   **MapStruct** (Mapeamento de objetos de alta performance)
*   **Lombok** (Produtividade e código limpo)
*   **Hibernate Validator**
*   **Clean Architecture (SOLID)**

---

*Davi Brito Silva - Desenvolvedor Backend em constante evolução.*
