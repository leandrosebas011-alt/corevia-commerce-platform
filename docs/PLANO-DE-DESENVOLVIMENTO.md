# Plano de desenvolvimento

Este documento registra o estado do projeto e organiza a evolução em entregas pequenas. A ideia é ter, a cada etapa, algo que possa ser executado, explicado em uma entrevista e mostrado no portfólio.

## Onde paramos

O projeto está sincronizado com `origin/main`. As pastas `Ensinamentos/` e `prints/` foram removidas. O usuário prefere conduzir o trabalho por interfaces gráficas e aprender em passos curtos, com os arquivos alterados sempre identificados para abertura no VS Code.

| Área | Estado observado |
|---|---|
| Projeto Java | Maven com Spring Boot e Java 21 configurado no `pom.xml`. |
| Inicialização | `ComercialApiApplication` inicia o Spring Boot. |
| API | `HelloController` expõe `GET /hello`; o usuário iniciou a aplicação e confirmou que a rota respondeu. |
| Banco | O banco local contém `categorias`, `clientes`, `produtos`, `pedidos`, `itens_pedido` e `movimentacoes_estoque`, com PKs, FKs, unicidades e checks documentados pelo DDL enviado pelo usuário. Flyway e o módulo PostgreSQL foram adicionados ao Maven; ainda faltam os scripts e a configuração de baseline. |
| Produto | A entidade mapeia as colunas principais de `portfolio_web.produtos`, mas ainda não expressa todas as constraints; `categoria_id` segue como `Long`. CRUD ainda não existe. |
| Banco | PostgreSQL está configurado por `PG_HOST`, `PG_PORT`, `PG_USER` e `PG_PASSWORD`; o banco esperado é `db_gestao_comercial`. |
| Frontend | React é uma intenção no README, ainda não há aplicação frontend. |
| Produto funcional | Clientes, estoque, pedidos, vendas, dashboard e autenticação ainda não foram implementados. |
| Publicação | Não há evidência nos arquivos revisados de deploy, imagens, licença ou documentação de API. |

O usuário confirmou que a aplicação iniciou e validou o mapeamento. Também compartilhou o DDL das seis tabelas. Flyway está no Maven, mas a aplicação não deve ser iniciada até os scripts e a estratégia de baseline estarem preparados e revisados, pois o Spring Boot executa migrations durante a inicialização.

## Ordem recomendada

### Etapa 1 — Preparar e compreender o ambiente

1. Instale/valide JDK 21 e PostgreSQL.
2. A chave primária de `Produto` já foi adicionada. Depois de validar a inicialização, defina os campos comerciais e as validações iniciais.
3. Crie o banco `db_gestao_comercial` e o schema `portfolio_web` com seu usuário local.
4. Defina `PG_HOST`, `PG_PORT`, `PG_USER` e `PG_PASSWORD` nas variáveis de usuário do Windows, pela janela **Variáveis de Ambiente**; reabra o VS Code depois.
5. Inicie a classe `ComercialApiApplication` pela ação **Run Java** do VS Code ou pelo Spring Boot Dashboard.
6. Acesse `http://localhost:8080/hello` e confira a resposta.

**Concluída quando:** a API inicia sem erro de mapeamento ou conexão e a rota responde localmente. Não salve senhas no Git. O usuário confirmou esta etapa concluída.

### Etapa 2 — Fechar o primeiro recurso: produtos

1. Alinhe o mapeamento da entidade Produto às regras confirmadas no DDL e defina as validações da API.
2. Complete a entidade JPA com chave primária e tipos adequados; decida como o schema e a tabela serão criados/atualizados. Para evolução consistente, prefira migrações versionadas em vez de depender de alterações automáticas no banco.
3. Crie um repositório Spring Data JPA.
4. Crie endpoints REST para criar e listar produtos; em seguida, consultar por id, atualizar e remover.
5. Valide entradas e devolva códigos HTTP coerentes, incluindo `404` para id inexistente e `400` para dados inválidos.
6. Registre exemplos de chamadas e respostas no README ou em documentação de API.

**Concluída quando:** os dados persistem no PostgreSQL e podem ser operados pela API, com validação e erros compreensíveis.

### Etapa 3 — Dar base de engenharia ao backend

1. Separe controller, serviço, repositório, entidade e DTOs conforme o recurso crescer.
2. Centralize o tratamento de erros e evite expor detalhes internos nas respostas.
3. Adicione testes para regras e endpoints; documente como executá-los.
4. Configure perfis e exemplos de variáveis (`.env.example` sem segredos), além de migrações do banco.

**Concluída quando:** outra pessoa consegue configurar, iniciar e validar a API seguindo apenas a documentação.

### Etapa 4 — Expandir o domínio

Implemente clientes, pedidos e itens de pedido. Depois trate as regras de estoque e vendas, transações e indicadores. Estabeleça as relações entre entidades e suas regras antes de adicionar telas de dashboard. Autenticação pode entrar quando os fluxos centrais estiverem estáveis.

**Concluída quando:** os fluxos comerciais principais funcionarem pela API e tiverem documentação e cobertura compatíveis com as regras.

### Etapa 5 — Construir o frontend

1. Crie uma aplicação React em uma pasta própria.
2. Comece por uma tela de produtos com listagem, formulário e estados de carregamento/erro.
3. Conecte a API e configure CORS para o endereço local do frontend.
4. Torne a interface responsiva e acessível; depois adicione clientes, pedidos e dashboard.

**Concluída quando:** uma pessoa consegue executar os fluxos principais pelo navegador sem chamar a API manualmente.

### Etapa 6 — Preparar a apresentação pública

1. Revise o README para refletir o que está funcionando, pré-requisitos, configuração, execução e exemplos.
2. Adicione imagens próprias do sistema e, quando possível, um link para demonstração online.
3. Escolha uma licença compatível com o uso pretendido e verifique que nenhum segredo, dado pessoal ou arquivo local foi commitado.
4. Faça commits pequenos com mensagens que expliquem a mudança e publique o branch no GitHub.
5. No LinkedIn, explique o problema, a solução, as tecnologias e o que você aprendeu. Inclua o link do repositório e da demonstração, sem anunciar funcionalidades que ainda estejam planejadas.

## Checklist para o próximo ciclo

- [x] Confirmar JDK 21 e conexão local ao PostgreSQL.
- [x] Executar `/hello` localmente.
- [x] Definir campos comerciais iniciais de Produto.
- [x] Alinhar `Produto` às colunas reais: incluir `sku` e `categoria_id`, mapear `estoque_atual` e ajustar os tamanhos/precisão.
- [ ] Definir validações de Produto e registrar migration para o schema/tabela.
- [x] Adicionar chave primária à entidade `Produto`.
- [x] Validar a inicialização e conexão local com PostgreSQL após o alinhamento atual da entidade (confirmado pelo usuário).
- [ ] Implementar repositório e endpoints de Produto.
- [ ] Documentar exemplos e configurar testes do recurso.
- [ ] Atualizar o README conforme a entrega real.

## Rascunho para publicação no LinkedIn

> Estou desenvolvendo o Corevia Commerce Platform, um projeto de portfólio para praticar a construção de uma plataforma de gestão comercial. Comecei pelo backend com Java 21 e Spring Boot e estou evoluindo a API em entregas pequenas, começando pelo cadastro de produtos com PostgreSQL. Próximo passo: [descrever a entrega concluída]. Código e instruções: [link do repositório].

Edite o texto entre colchetes e publique somente quando o recurso citado estiver disponível no repositório.
