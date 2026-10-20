# cadastro-de-colaborador

API REST para cadastro de colaboradores dos tipos **Padrão**, **Comissionado** e **Produção** e geração da folha de pagamento consolidada.

- **Padrão:** recebe apenas o salário base.
- **Comissionado:** salário base + comissão (`valorDasVendas × percentualDeComissao ÷ 100`, arredondada em 2 casas), calculada automaticamente no cadastro e na atualização.
- **Produção:** salário base + `quantidadeProduzida × valorPagoPorUnidade`.

## Tecnologias

- Java 21 / Spring Boot 4.1.1 / Gradle (wrapper incluído)
- PostgreSQL 17 + Flyway
- Spring Data JPA, Bean Validation, Lombok
- SpringDoc OpenAPI (Swagger UI)
- JUnit 5 + JaCoCo
- Docker Compose

## Pré-requisitos

- Java 21
- Docker e Docker Compose

## Como executar

1. Crie o arquivo `.env` na raiz do projeto (ele é lido apenas pelo Docker Compose para criar o banco):
   ```
   DATABASE_USERNAME=admin
   DATABASE_PASSWORD=sua_senha
   ```

2. Suba o banco de dados:
   ```bash
   docker compose up -d postgres
   ```

3. Defina as variáveis de ambiente e execute a aplicação. O Spring **não** lê o arquivo `.env`, por isso as variáveis precisam estar no terminal:

   Linux/macOS:
   ```bash
   export DATABASE_URL=jdbc:postgresql://localhost:5432/colaborador
   export DATABASE_USERNAME=admin
   export DATABASE_PASSWORD=sua_senha
   ./gradlew bootRun
   ```

   Windows (PowerShell):
   ```powershell
   $env:DATABASE_URL="jdbc:postgresql://localhost:5432/colaborador"
   $env:DATABASE_USERNAME="admin"
   $env:DATABASE_PASSWORD="sua_senha"
   .\gradlew.bat bootRun
   ```

4. A API fica disponível em `http://localhost:8080`. As tabelas são criadas automaticamente pelo Flyway na primeira execução.

> O serviço `java` do `docker-compose.yml` é apenas um contêiner com JDK. Para rodar a aplicação dentro dele, é necessário publicar a porta `8080`, repassar as variáveis de banco ao serviço e usar `jdbc:postgresql://postgres:5432/colaborador` como `DATABASE_URL`.

## Endpoints

| Operação | Padrão | Comissionado | Produção |
|---|---|---|---|
| Cadastrar | `POST /v1/colaborador` | `POST /v1/colaborador/comissionado` | `POST /v1/colaborador/producao` |
| Consultar por ID | `GET /v1/colaborador/{id}` | `GET /v1/colaborador/comissionado/{id}` | `GET /v1/colaborador/producao/{id}` |
| Listar todos do tipo | `GET /v1/colaborador/all` | `GET /v1/colaborador/comissionado/all` | `GET /v1/colaborador/producao/all` |
| Atualizar | `PUT /v1/colaborador/{id}` | `PUT /v1/colaborador/comissionado/{id}` | `PUT /v1/colaborador/producao/{id}` |
| Excluir | `DELETE /v1/colaborador/{id}` | `DELETE /v1/colaborador/comissionado/{id}` | `DELETE /v1/colaborador/producao/{id}` |

| Folha de pagamento | |
|---|---|
| `GET /v1/folha-de-pagamento` | Retorna em JSON todos os colaboradores (ordenados por ID) com salário base, adicional e salário final, além do total da folha |

O tipo do colaborador é definido pelo endpoint usado. Os endpoints `/all` retornam um objeto no formato `{ "id": colaborador }`.

### Exemplos de requisição

Padrão:
```json
{ "nome": "Joao Moraes", "matricula": "PAD001", "salario": 2700 }
```

Comissionado:
```json
{ "nome": "Maria", "matricula": "COM001", "salario": 12333,
  "valorDasVendas": 5000, "percentualDeComissao": 10 }
```

Produção:
```json
{ "nome": "Carlos", "matricula": "PRD001", "salario": 4200,
  "quantidadeProduzida": 100, "valorPagoPorUnidade": 42 }
```

### Respostas de erro

- `404` — colaborador não encontrado para o ID informado (no tipo consultado).
- `400` — requisição inválida (ex.: comissionado sem valor das vendas ou percentual).

## Documentação (Swagger)

Com a aplicação rodando:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI (JSON): `http://localhost:8080/v3/api-docs`

## Banco de dados

- **SGBD:** PostgreSQL 17 (banco `colaborador`, porta `5432`)
- **Migração:** Flyway (`src/main/resources/db/migration/V1__create_colaborador_tables.sql`)
- **DDL:** `spring.jpa.hibernate.ddl-auto=validate` — alterações de tabela devem ser feitas com novas migrações (`V2__...sql`)
- **Tabelas:** `colaborador` (dados comuns) e `colaborador_padrao`, `colaborador_comissionado`, `colaborador_producao` (herança JPA `JOINED`, com `ON DELETE CASCADE`)

## Testes

```bash
./gradlew test
```

O relatório de cobertura do JaCoCo é gerado em `build/reports/jacoco/test/html/index.html`.

> O teste atual (`contextLoads`) inicializa o contexto completo do Spring, por isso exige o banco rodando e as variáveis de ambiente definidas.

## Estrutura do projeto

```
src/main/java/com/cadastro/colaborador/
├── ColaboradorApplication.java   # classe principal
├── controller/                   # REST controllers (colaboradores, paycheck)
├── service/                      # regras de negócio e cálculo da folha
├── entity/                       # entidades JPA e enum TipoColaborador
├── repository/                   # acesso aos dados (Spring Data JPA)
├── request/ e response/          # DTOs de entrada e saída
├── mapper/                       # conversões entre entidades e DTOs
├── exception/                    # tratamento de erros
└── swagger/                      # documentação OpenAPI dos endpoints
src/main/resources/
├── application.yaml
└── db/migration/                 # migrações Flyway
```

## Limitações conhecidas

- Exportação da folha em arquivo (`GeradorDeFolha` / `Relatorio.txt`) ainda não implementada.
- Não há autenticação.
- Não há endpoint único que liste colaboradores de todos os tipos.
