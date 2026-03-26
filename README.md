# API Sistema de Gestão de Concessionária

Este projeto é uma aplicação **Spring Boot** autêntica, desenvolvida para gerenciar de forma profissional as operações de uma concessionária de veículos, abrangendo desde a gestão de estoque até o fluxo completo de vendas e comissionamento.

O foco principal deste desenvolvimento foi a aplicação de **Clean Architecture** e **SOLID** e padrões nativos de Design de Software, garantindo um "Core" (Regras de Negócio) sejam independentes dos detalhes de infraestrutura (como banco de dados ou frameworks).

---

## 🏗️ Arquitetura do Sistema

A aplicação segue os princípios da **Arquitetura Limpa**, organizada em camadas bem definidas para separação de responsabilidades (SoC):

### 1. Core 
*   **Models:** Representam as entidades do mundo real (`Concessionaria`, `Veiculo`, `Vendedor`, `Venda`) usando **Records**, garantindo imutabilidade e clareza no transporte de dados.
*   **UseCases:** Cada ação do sistema é um caso de uso isolado (ex: `CriarClienteVendaUseCase`). Isso facilita a manutenção e evita "classes gigantes" que fazem tudo.
*   **Gateways (Interfaces):** São contratos de como o Core deve se comunicar com o mundo externo, sem saber se esta usando um banco SQL, NoSQL ou uma API externa.
*   **Exception Hierarchy:** Hierarquia robusta de exceções de domínio (`DomainException` -> `NotFoundException` -> específicas), impedindo o vazamento de exceções infraestruturais (como SQL) nas regras de negócio.

### 2. Infraestrutura 
*   **Persistence:** Utilizei o **Spring Data JPA** com entidades Hibernate robustas, aplicando mapeamentos complexos de `OneToMany` e `OneToOne`.
*   **GatewayImpl:** Onde o contrato do Core é cumprido, realizando o acesso real aos dados.
*   **Controllers & RestAdvice:** Camada de entrega REST blindada por um **GlobalExceptionHandler** para tratamento universal e padronizado de códigos HTTP (400, 404, 500).
*   **DTOs (Data Transfer Objects):** Apliquei o padrão DTO para blindar as entidades. O banco de dados nunca é exposto diretamente à API, protegendo a integridade do sistema.
*   **MapStruct:** Toda a conversão entre Domínio, Entidade e DTO é feita de forma automatizada e performática pelo MapStruct, evitando código repetitivo.

---

## Resolvendo Desafios: Referências Circulares em JSON

Um dos maiores desafios técnicos enfrentados (e resolvido) neste projeto foi o **Loop Infinito do Jackson (Referência Circular)**.

**Problema:**
No banco de dados, a `Concessionaria` possui uma lista de `Vendedores`, e cada `Vendedor` possui um link direto de volta para a sua `Concessionaria`. Ao tentar gerar um JSON, o sistema entrava em um loop infinito, tentando serializar um dentro do outro sem parar.

**Solução:**
Em vez de utilizar anotações genéricas como `@JsonIgnore`, optei por uma solução de arquitetura mais robusta:
1.  **Isolamento via DTOs:** Criei o `VendedorResponse` com campos simples e **apenas o UUID** da Concessionária.
2.  **Mappers Inteligentes:** Configurei os Mappers para extrair os IDs das entidades relacionadas no momento da conversão.
3.  **Resultado:** O JSON gerado é limpo, leve e performático, eliminando qualquer risco de estouro de memória ou loops circulares.

---

## Qualidade de Código (Testes Automatizados)

Para garantir a robustez das regras de negócio e a facilidade de manutenção, implementei **Testes Unitários** de alta performance cobrindo todos os UseCases do sistema.

- **Estrutura AAA (Arrange, Act, Assert):** Testes organizados para máxima clareza e previsibilidade de resultados.
- **JUnit 5 & Mockito:** Utilização de Mocks para isolar a lógica de negócio de dependências da camada de infraestrutura.
- **ArgumentCaptor:** Técnica aplicada para capturar e validar os dados exatos que são transacionados entre as camadas do sistema.
- **Cobertura de Exceções:** Garantia de que o sistema reage corretamente a falhas e regras de negócio violadas, não apenas a fluxos de sucesso.

---

## Tecnologias Utilizadas

*   **Java 21**
*   **Spring Boot**
*   **Spring Data JPA** e **H2 Database** (Em memória)
*   **JUnit 5** e **Mockito** 
*   **MapStruct** (Mapeamento de objetos de alta performance)
*   **Lombok** (Produtividade e código limpo)
*   **Hibernate Validator**
*   **Arquitetura Baseada em Clean Architecture**

---

Davi Brito Silva - *Desenvolvedor Backend em constante evolução.*
