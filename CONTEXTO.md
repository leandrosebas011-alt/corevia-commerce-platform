# Contexto para continuar o projeto

Leia este arquivo ao retomar o trabalho. Consulte o [README](README.md), o [plano de desenvolvimento](docs/PLANO-DE-DESENVOLVIMENTO.md) e o índice Word de [Ensinamentos](Ensinamentos/Indice.docx).

## Objetivo

Construir o Corevia Commerce Platform como projeto de portfólio para GitHub e LinkedIn. O usuário quer ser guiado passo a passo e pediu que cada trabalho realizado seja registrado em um arquivo do projeto, para que o contexto sobreviva ao fechamento do terminal/sessão.

**Regra de continuidade:** ao concluir cada etapa, atualize este arquivo com o que foi feito, decisões tomadas, validações executadas ou pendentes e a próxima ação. Atualize também o README ou o plano quando o conteúdo geral mudar. As explicações didáticas detalhadas ficam somente em documentos Word (`.docx`) na pasta `Ensinamentos/`; para cada arquivo de código criado ou alterado, mantenha uma aula correspondente nessa pasta.

## Como orientar o usuário

O usuário quer aprender durante a construção do projeto. Trabalhe em etapas pequenas e didáticas: explique o objetivo e os conceitos antes de propor uma alteração; mostre ou oriente uma mudança por vez; explique como conferir o resultado e o que cada parte faz; depois registre o aprendizado e o estado neste arquivo. Evite fazer várias etapas de uma vez sem explicar. Quando fizer sentido, deixe o usuário executar uma ação e compartilhar o resultado para que possam analisar juntos.

## Estado atual

- Backend Maven/Spring Boot em `comercial-api/`, Java 21.
- PostgreSQL configurado por `PG_HOST`, `PG_PORT`, `PG_USER` e `PG_PASSWORD`; banco esperado: `db_gestao_comercial`; schema: `portfolio_web`.
- `GET /hello` está implementado em `HelloController`.
- `Produto` mapeia para `portfolio_web.Produtos` e agora tem o campo `Long id` com `@Id` e `@GeneratedValue(strategy = GenerationType.IDENTITY)`.
- O usuário informou que já tem instalado tudo que precisa para executar o projeto.
- O backend foi iniciado com sucesso após adicionar o identificador. O log confirma Java 21.0.12.1, Spring Boot 4.1.2, Tomcat na porta 8080 e conexão PostgreSQL em `localhost:5432`, banco `db_gestao_comercial`, PostgreSQL 18.6.
- O usuário confirmou que `http://localhost:8080/hello` respondeu corretamente.
- `Produto` foi alinhada à imagem da tabela: `id`, `categoria_id`, `sku`, `nome`, `descricao`, `preco`, `estoque_atual` e `ativo`. `categoria_id` está como `Long` simples por enquanto; uma entidade/relação `Categoria` pode ser criada depois como etapa de aprendizado JPA.
- Ainda não há repositório, serviço, endpoints de produto ou frontend.
- O usuário pediu que as explicações de aprendizado fiquem em documentos Word na pasta `Ensinamentos/`, com uma aula por arquivo de código ou configuração criado/alterado. `Ensinamentos/Indice.docx` lista as aulas para `Produto.java` e `application.properties`.
- `application.properties` agora configura `spring.jpa.hibernate.ddl-auto=validate` para o Hibernate verificar a estrutura existente ao iniciar, sem criar ou modificar tabelas. A aula está em `Ensinamentos/application.properties.docx`. A aplicação deve ser reiniciada para observar o resultado.
- A captura `prints/Dados da tabela.png` mostra a estrutura existente da tabela `portfolio_web.produtos`: `id bigint` (PK), `categoria_id bigint`, `sku varchar(40)`, `nome varchar(150)`, `descricao varchar(255)`, `preco numeric(12,2)`, `estoque_atual integer` e `ativo boolean`.
- Divergências da entidade atual: faltam `categoria_id` e `sku`; `nome` tem tamanho 120 em vez de 150; `descricao` tem tamanho 500 em vez de 255; `preco` usa precisão 10 em vez de 12; `quantidadeEstoque` não corresponde ao nome da coluna `estoque_atual`.

## Última alteração

Após autorização do usuário, a entidade JPA foi alinhada à tabela existente: `@Table(name = "produtos")`; `categoriaId` como coluna `categoria_id`; `sku` com 40 caracteres; `nome` com 150; `descricao` com 255; `preco` como `BigDecimal` `numeric(12,2)`; e `estoqueAtual` como coluna `estoque_atual`. `ativo` mantém valor inicial `true`. A captura não mostra nullabilidade, então não foram inventadas restrições `nullable`. `categoria_id` é mapeado inicialmente como `Long`; mais adiante pode virar uma associação `@ManyToOne` com uma entidade `Categoria`.

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

1. Usuário deve reiniciar a aplicação e compartilhar o resultado da validação automática do esquema.
2. Se a validação passar, registrar uma migration para reproduzir/versionar a estrutura existente e depois implementar repository e operações da API de produtos.

## Validações recentes

- `git diff --check` foi executado após as alterações de documentação e da entidade; não apontou erros de whitespace. Git exibiu apenas um aviso de conversão de LF para CRLF no `README.md`.
- A aplicação foi iniciada pelo usuário com `.\mvnw.cmd spring-boot:run`; o log mostra `Started ComercialApiApplication` e processo ativo. O PostgreSQL conectou com sucesso.
- O usuário confirmou que `GET /hello` respondeu corretamente com o backend em execução.
- A saída mostra compilação da fonte de testes pelo Maven, mas nenhum teste automatizado foi executado nesta sessão.
