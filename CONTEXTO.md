# Contexto para continuar o projeto

Leia este arquivo ao retomar o trabalho. Consulte também o [README](README.md) e o [plano de desenvolvimento](docs/PLANO-DE-DESENVOLVIMENTO.md).

## Objetivo

Construir o Corevia Commerce Platform como projeto de portfólio para GitHub e LinkedIn. O usuário quer ser guiado passo a passo e pediu que cada trabalho realizado seja registrado em um arquivo do projeto, para que o contexto sobreviva ao fechamento do terminal/sessão.

**Regra de continuidade:** ao concluir cada etapa, atualize este arquivo com o que foi feito, decisões tomadas, validações executadas ou pendentes e a próxima ação. Atualize também o README ou o plano quando o conteúdo geral mudar. O usuário prefere aprender pela interface e em passos curtos: explique o objetivo e os conceitos antes de cada mudança, faça uma etapa por vez e indique sempre os arquivos criados ou alterados para que ele possa abri-los no VS Code. As pastas `Ensinamentos/` e `prints/` foram removidas; não referenciá-las como conteúdo existente.

## Como orientar o usuário

O usuário quer aprender durante a construção do projeto. Trabalhe em etapas pequenas e didáticas: explique o objetivo e os conceitos antes de propor uma alteração; mostre ou oriente uma mudança por vez; explique como conferir o resultado e o que cada parte faz; depois registre o aprendizado e o estado neste arquivo. Evite fazer várias etapas de uma vez sem explicar. Quando fizer sentido, deixe o usuário executar uma ação e compartilhar o resultado para que possam analisar juntos.

## Estado atual

- Backend Maven/Spring Boot em `comercial-api/`, Java 21.
- PostgreSQL configurado por `PG_HOST`, `PG_PORT`, `PG_USER` e `PG_PASSWORD`; banco esperado: `db_gestao_comercial`; schema: `portfolio_web`.
- `GET /hello` está implementado em `HelloController`.
- `Produto` mapeia para `portfolio_web.produtos`, incluindo `Long id` com `@Id` e `@GeneratedValue(strategy = GenerationType.IDENTITY)`.
- O usuário informou que já tem instalado tudo que precisa para executar o projeto.
- O backend foi iniciado com sucesso após adicionar o identificador. O log confirma Java 21.0.12.1, Spring Boot 4.1.2, Tomcat na porta 8080 e conexão PostgreSQL em `localhost:5432`, banco `db_gestao_comercial`, PostgreSQL 18.6.
- O usuário confirmou que `http://localhost:8080/hello` respondeu corretamente.
- `Produto` foi alinhada à imagem da tabela: `id`, `categoria_id`, `sku`, `nome`, `descricao`, `preco`, `estoque_atual` e `ativo`. `categoria_id` está como `Long` simples por enquanto; uma entidade/relação `Categoria` pode ser criada depois como etapa de aprendizado JPA.
- Ainda não há repositório, serviço, endpoints de produto ou frontend.
- O usuário prefere realizar ações por interfaces gráficas, não configurar variáveis pelo terminal, e quer ser conduzido passo a passo. O README descreve a configuração das variáveis de usuário pelas telas do Windows e a inicialização pelo VS Code.
- `application.properties` configura `spring.jpa.hibernate.ddl-auto=validate` para o Hibernate verificar a estrutura existente ao iniciar, sem criar ou modificar tabelas. O usuário confirmou que já conferiu as variáveis do sistema e que a aplicação iniciou corretamente após o alinhamento da entidade.
- O usuário compartilhou o DDL das seis tabelas de `portfolio_web`: `categorias`, `clientes`, `produtos`, `pedidos`, `itens_pedido` e `movimentacoes_estoque`. Todas têm `id bigint` identity e PK.
- Relações/dependências: `produtos.categoria_id` referencia `categorias.id`; `pedidos.cliente_id` referencia `clientes.id`; `itens_pedido.pedido_id` referencia `pedidos.id` e `produto_id` referencia `produtos.id`; `movimentacoes_estoque.produto_id` referencia `produtos.id`.
- Restrições importantes: categoria tem `nome` único; cliente tem `email` único; produto tem `sku` único, preço e estoque não negativos; pedido tem `numero` único, `status` limitado a PENDENTE/PAGO/ENVIADO/ENTREGUE/CANCELADO e total não negativo; item tem uma linha por par pedido/produto, quantidade positiva, preço unitário não negativo e `subtotal` calculado pelo banco; movimentação tem `referencia` única, tipo ENTRADA/SAIDA e quantidade positiva. Clientes, produtos, pedidos e itens têm outros campos obrigatórios e defaults conforme o DDL fornecido.
- O campo `subtotal` de `itens_pedido` é uma coluna gerada armazenada. `estoque_atual` não aparece como derivado das movimentações no DDL; atualizar o saldo ao registrar uma movimentação dependerá de lógica da aplicação ou de trigger ainda não identificado.
- As FKs usam `ON DELETE NO ACTION`; registros referenciados não podem ser removidos enquanto houver dependentes. `OWNER`, `TABLESPACE` e collation padrão do PostgreSQL no script local são detalhes de ambiente, não devem ser copiados sem necessidade para uma migration portátil.
- A migration deve criar tabelas numa ordem compatível com as FKs: `categorias`, `clientes`, `produtos`, `pedidos`, depois `itens_pedido` e `movimentacoes_estoque` (estas duas podem vir após suas dependências). O banco local já existe e contém essas tabelas; é preciso decidir estratégia de baseline para evitar recriá-las.
- O `pom.xml` agora inclui `spring-boot-starter-flyway` e o módulo de banco `flyway-database-postgresql`. Ainda não há scripts de migration nem configuração de baseline. Não iniciar a aplicação até preparar e revisar esses itens, pois o Spring Boot executa as migrations durante a inicialização.
- O mapeamento Java atual só tem a entidade `Produto`. Seus nomes/tipos básicos coincidem, mas não expressa todas as constraints do DDL e `categoriaId` ainda é um `Long` simples. Alinhar cada entidade e regra em etapas antes de criar os scripts.

## Última alteração no código

Após autorização do usuário, a entidade foi alinhada às colunas e tipos da tabela existente. O DDL compartilhado depois confirmou também as restrições e padrões descritos acima. `categoria_id` permanece como `Long` simples até a entidade `Categoria` ser estudada.

## Atualização de documentação

README e plano agora orientam configurar as variáveis PostgreSQL pela interface do Windows e iniciar a aplicação pelo VS Code. Referências às pastas removidas `Ensinamentos/` e `prints/` foram retiradas. O usuário confirmou que já testou a inicialização e a validação do mapeamento. O DDL completo das seis tabelas foi compartilhado; Flyway e o módulo PostgreSQL foram adicionados ao Maven. Próximo passo: criar e revisar a migration inicial e o baseline sem iniciar a aplicação.

## Nota de aprendizado: identificador JPA

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

- `private Long id;` declara um campo chamado `id`. `private` restringe acesso direto a esse campo; `Long` é um tipo objeto que pode ser `null` antes de haver registro.
- `@Id` marca o campo como chave primária JPA, isto é, o valor que distingue este produto dos demais.
- `@GeneratedValue(...)` configura o JPA para obter o identificador automaticamente.
- `GenerationType.IDENTITY` usa a geração de identidade do banco: ao inserir uma linha, o banco cria o próximo número e o JPA lê o valor gerado de volta para o objeto.

Exemplo conceitual: antes de salvar, o objeto está com `id == null`; após a inserção, pode estar com `id == 1` (ou outro número disponível). `long`, ao contrário de `Long`, é primitivo e começa em `0`, não representando a ausência de identificador da mesma forma. Como `@Id` está no campo, o JPA usa acesso por campo; getters e setters podem ser adicionados para o restante do código acessar os dados de forma controlada.

## Próxima ação

1. Explicar como migration e baseline funcionam e escolher a abordagem segura para o banco local já existente, sem alterar o banco.
2. Criar e revisar o script inicial com as seis tabelas na ordem das dependências e configurar baseline.
3. Alinhar o modelo Java e validações em passos pequenos, começando por Produto e Categoria.
4. Depois de registrar a estrutura, implementar repository e operações da API de produtos.

## Validações recentes

- O usuário confirmou que as variáveis `PG_HOST`, `PG_PORT`, `PG_USER` e `PG_PASSWORD` estão corretas nas Variáveis do Sistema e que já havia testado com sucesso a inicialização após o alinhamento de `Produto`.
- `git diff --check` foi executado após as alterações de documentação e da entidade; não apontou erros de whitespace. Git exibiu apenas um aviso de conversão de LF para CRLF no `README.md`.
- A aplicação foi iniciada pelo usuário com `.\mvnw.cmd spring-boot:run`; o log mostra `Started ComercialApiApplication` e processo ativo. O PostgreSQL conectou com sucesso.
- O usuário confirmou que `GET /hello` respondeu corretamente com o backend em execução.
- A saída mostra compilação da fonte de testes pelo Maven, mas nenhum teste automatizado foi executado nesta sessão.
