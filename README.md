# Corevia Commerce Platform

Projeto de portfólio para construir, passo a passo, uma plataforma web de gestão comercial. A proposta é evoluir de uma API Java inicial para uma aplicação full stack com cadastro de produtos e clientes, estoque, pedidos e indicadores.

> **Estado atual:** início do backend. A aplicação Spring Boot iniciou localmente, conectou ao PostgreSQL e `GET /hello` respondeu corretamente antes do alinhamento recente da entidade. Agora o Hibernate está configurado para validar o mapeamento ao iniciar; falta reiniciar para conferir. A tabela ainda precisa ser versionada no projeto e não há endpoints comerciais.

## Tecnologias previstas

- **Backend:** Java 21, Spring Boot, Spring Web MVC, Spring Data JPA, Bean Validation e Maven.
- **Banco de dados:** PostgreSQL.
- **Frontend planejado:** React, JavaScript, HTML e CSS.
- **Versionamento:** Git e GitHub.

## O que já existe

- Projeto Maven em `comercial-api/` com Spring Boot.
- Classe principal `ComercialApiApplication`.
- Endpoint de demonstração `GET /hello`.
- Entidade JPA `Produto` mapeia `id`, `categoria_id`, `sku`, `nome`, `descricao`, `preco`, `estoque_atual` e `ativo`, conforme a tabela local registrada em [`prints/Dados da tabela.png`](prints/Dados%20da%20tabela.png).
- Configuração para PostgreSQL por variáveis de ambiente.
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

O arquivo `application.properties` exige `PG_HOST`, `PG_PORT`, `PG_USER` e `PG_PASSWORD`. A aplicação também precisa conseguir conectar ao PostgreSQL ao iniciar, pois o Spring configura JPA e datasource.

No PowerShell, configure as variáveis apenas para a sessão atual (substitua os valores pelos seus):

```powershell
$env:PG_HOST = "localhost"
$env:PG_PORT = "5432"
$env:PG_USER = "seu_usuario"
$env:PG_PASSWORD = "sua_senha"
```

Na sequência, na pasta `comercial-api`, inicie a aplicação:

```powershell
cd comercial-api
.\mvnw.cmd spring-boot:run
```

Com a aplicação iniciada, abra `http://localhost:8080/hello`. A resposta esperada é uma mensagem confirmando que a API está funcionando.

> Não compartilhe credenciais nem publique um arquivo `.env` real. O `.gitignore` já exclui arquivos `.env`.

## Próximos passos

O próximo incremento recomendado é validar o mapeamento atualizado, registrar uma migração versionada e então implementar repositório e endpoints REST com validação. O roteiro completo está em [`docs/PLANO-DE-DESENVOLVIMENTO.md`](docs/PLANO-DE-DESENVOLVIMENTO.md). Para retomar o trabalho em outra sessão, comece por [`CONTEXTO.md`](CONTEXTO.md).

Para aprender sobre cada arquivo de código ou configuração alterado, consulte o índice Word de [Ensinamentos](Ensinamentos/Indice.docx). As aulas atuais são [Produto.java](Ensinamentos/Produto.java.docx) e [application.properties](Ensinamentos/application.properties.docx).

## Portfólio e LinkedIn

Ao publicar uma versão demonstrável, atualize este README com capturas de tela, instruções reproduzíveis, decisões técnicas e link do deploy. Uma apresentação pública deve descrever apenas funcionalidades realmente implementadas. O plano inclui um checklist para GitHub e uma sugestão de texto para LinkedIn.

## Licença

Ainda não definida. Antes de tornar o repositório público, escolha uma licença se quiser permitir reutilização do código por outras pessoas.
