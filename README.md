# Corevia Commerce Platform

Projeto de portfólio para construir, passo a passo, uma plataforma web de gestão comercial. A proposta é evoluir de uma API Java inicial para uma aplicação full stack com cadastro de produtos e clientes, estoque, pedidos e indicadores.

> **Estado atual:** início do backend. Após alinhar a entidade `Produto`, o usuário confirmou que a aplicação iniciou corretamente com a validação do mapeamento (`ddl-auto=validate`) e que a conexão ao PostgreSQL funcionou. A tabela ainda precisa ser versionada no projeto e não há endpoints comerciais.

## Tecnologias previstas

- **Backend:** Java 21, Spring Boot, Spring Web MVC, Spring Data JPA, Bean Validation e Maven.
- **Banco de dados:** PostgreSQL.
- **Migrações do banco:** Flyway (script inicial e baseline ainda em preparação).
- **Frontend planejado:** React, JavaScript, HTML e CSS.
- **Versionamento:** Git e GitHub.

## O que já existe

- Projeto Maven em `comercial-api/` com Spring Boot.
- Classe principal `ComercialApiApplication`.
- Endpoint de demonstração `GET /hello`.
- Entidade JPA `Produto` mapeia as colunas `id`, `categoria_id`, `sku`, `nome`, `descricao`, `preco`, `estoque_atual` e `ativo` da tabela existente `portfolio_web.produtos`.
- O banco local já possui tabelas para categorias, clientes, produtos, pedidos, itens de pedido e movimentações de estoque; as operações comerciais pela API ainda não foram implementadas.
- Configuração para PostgreSQL por variáveis de ambiente.
- O projeto usa somente o banco existente `db_gestao_comercial`. Flyway e o baseline versão 1 estão configurados para `portfolio_web`; o histórico ainda precisa ser registrado na próxima inicialização. V1 não deve recriar as tabelas existentes.
- Teste de contexto gerado pelo Spring Boot, ainda sem validação do fluxo comercial.

## Funcionalidades planejadas

- Cadastro, consulta, edição e remoção de produtos e clientes.
- Controle de estoque.
- Registro e acompanhamento de pedidos e vendas.
- Dashboard com indicadores comerciais.
- Autenticação e controle de acesso.
- Interface responsiva para consumir a API.

## Como executar o backend

### Pré-requisitos

- JDK 21.
- PostgreSQL instalado e em execução.
- Um banco chamado `db_gestao_comercial` e um schema `portfolio_web` acessíveis pelo usuário configurado.

O arquivo `application.properties` lê as variáveis `PG_HOST`, `PG_PORT`, `PG_USER` e `PG_PASSWORD`. Para configurá-las pelas telas do Windows:

1. Abra o menu Iniciar e pesquise por **Editar as variáveis de ambiente do sistema**.
2. Na janela **Propriedades do Sistema**, clique em **Variáveis de Ambiente...**.
3. Na seção superior **Variáveis de usuário**, clique em **Novo...** para cada variável. Se ela já existir, selecione-a e clique em **Editar...**.
4. Use os valores da sua instalação local: `PG_HOST` = `localhost`, `PG_PORT` = `5432`, `PG_USER` = seu usuário PostgreSQL e `PG_PASSWORD` = a senha desse usuário.
5. Confirme as janelas e feche/reabra o VS Code para que os novos processos recebam as variáveis.

No VS Code, abra `comercial-api/src/main/java/com/portfolio/comercial/ComercialApiApplication.java` e use **Run** acima do método `main` (ou a ação **Run Java**). Se essa ação não aparecer, instale as extensões Java/Spring do VS Code ou use a seção **Spring Boot Dashboard**. Com a aplicação iniciada, abra `http://localhost:8080/hello` no navegador.

As variáveis de usuário ficam salvas no perfil do Windows e são herdadas por novos programas. Não compartilhe `PG_PASSWORD`, não a inclua em capturas de tela e não a salve em arquivos versionados.

Com a aplicação iniciada, abra `http://localhost:8080/hello`. A resposta esperada é uma mensagem confirmando que a API está funcionando.

> Não compartilhe credenciais nem publique um arquivo `.env` real. O `.gitignore` já exclui arquivos `.env`.

## Próximos passos

O próximo incremento recomendado é revisar a configuração e iniciar a aplicação para registrar a versão 1 no histórico do Flyway; futuras migrations começarão na versão 2. O roteiro completo está em [`docs/PLANO-DE-DESENVOLVIMENTO.md`](docs/PLANO-DE-DESENVOLVIMENTO.md). Para retomar o trabalho em outra sessão, comece por [`CONTEXTO.md`](CONTEXTO.md). As explicações serão feitas passo a passo nesta conversa, e informarei sempre os arquivos que eu criar ou alterar para que você possa abri-los no VS Code.

## Portfólio e LinkedIn

Ao publicar uma versão demonstrável, atualize este README com capturas de tela, instruções reproduzíveis, decisões técnicas e link do deploy. Uma apresentação pública deve descrever apenas funcionalidades realmente implementadas. O plano inclui um checklist para GitHub e uma sugestão de texto para LinkedIn.

## Licença

Ainda não definida. Antes de tornar o repositório público, escolha uma licença se quiser permitir reutilização do código por outras pessoas.
