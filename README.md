# Fast API — Catálogo de Produtos

API REST desenvolvida com **Java e Spring Boot** para gerenciar um catálogo de produtos. O projeto foi criado como prática de desenvolvimento backend, com persistência em banco de dados relacional, validação de entradas, paginação, ordenação e filtros dinâmicos.

## Tecnologias

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA / Hibernate
- Bean Validation
- PostgreSQL
- Maven
- Docker Compose
- Lombok

## Funcionalidades

- Cadastrar produtos.
- Consultar um produto pelo ID.
- Listar produtos com paginação e ordenação.
- Filtrar produtos por nome, categoria, faixa de preço e estoque mínimo.
- Atualizar parcialmente os dados de um produto.
- Desativar um produto sem removê-lo do banco.
- Excluir um produto.
- Validar dados de entrada e centralizar o tratamento de exceções.

## Estrutura do projeto

```text
src/main/java/com/viratech/
├── controller/       # Endpoints REST
├── domain/           # Entidade Product
├── dto/              # Objetos de entrada, filtro e resposta
├── enums/            # Categorias de produto
├── exceptions/       # Exceções e tratamento global de erros
├── mapper/           # Conversão entre DTOs e entidade
├── repository/       # Acesso a dados com Spring Data JPA
├── service/          # Regras e casos de uso da aplicação
└── specification/    # Construção dos filtros dinâmicos
```

## Pré-requisitos

Para executar localmente, tenha instalado:

- Java 17 ou compatível com a versão configurada no projeto;
- Docker e Docker Compose;
- Git (opcional, para clonar o repositório).

## Como executar

### 1. Inicie o PostgreSQL

Na pasta do projeto, execute:

```bash
docker compose up -d
```

O arquivo `docker-compose.yaml` configura o PostgreSQL com os seguintes parâmetros de desenvolvimento:

| Configuração | Valor |
|---|---|
| Host | `localhost` |
| Porta publicada | `5400` |
| Banco | `db-test` |
| Usuário | `postgres` |
| Senha | `admin` |

> Essas credenciais são apenas para desenvolvimento local. Não as utilize em ambientes de produção.

### 2. Execute a aplicação

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

No Windows:

```bat
mvnw.cmd spring-boot:run
```

A aplicação estará disponível em:

```text
http://localhost:1010
```

A configuração atual está em `src/main/resources/application.yaml`. O Hibernate está configurado com `ddl-auto: update` para o ambiente de desenvolvimento.

### 3. Execute os testes

Linux/macOS:

```bash
./mvnw test
```

Windows:

```bat
mvnw.cmd test
```

## Endpoints

A URL base dos recursos é `/products`.

| Método | Endpoint | Descrição | Resposta esperada |
|---|---|---|---|
| `POST` | `/products` | Cadastra um produto | `201 Created` |
| `GET` | `/products` | Lista e filtra produtos com paginação | `200 OK` |
| `GET` | `/products/{id}` | Consulta produto por ID | `200 OK` |
| `PATCH` | `/products/{id}` | Atualiza parcialmente um produto | `200 OK` |
| `PATCH` | `/products/{id}/deactivate` | Desativa um produto | `200 OK` |
| `DELETE` | `/products/{id}` | Exclui um produto | `204 No Content` |

Quando o produto não é encontrado, a API utiliza tratamento de exceção para retornar um erro. Entradas inválidas também são tratadas pela camada global de exceções.

## Exemplos de uso

Os exemplos abaixo usam `http://localhost:1010` como endereço base.

### Cadastrar produto

`POST /products`

```json
{
  "name": "Teclado mecânico",
  "description": "Teclado mecânico com conexão USB",
  "price": 249.90,
  "category": "electronics",
  "stock": 15
}
```

O campo `category` aceita os valores definidos pela API: `electronics`, `clothing`, `food`, `books` e `other`.

### Listar produtos

`GET /products`

A listagem aceita os parâmetros de paginação e ordenação do Spring Data, além dos filtros implementados:

| Parâmetro | Descrição | Exemplo |
|---|---|---|
| `page` | Número da página, começando em zero | `page=0` |
| `size` | Quantidade de itens por página | `size=10` |
| `sort` | Campo e direção da ordenação | `sort=price,asc` |
| `name` | Filtro por nome | `name=teclado` |
| `category` | Categoria | `category=electronics` |
| `minPrice` | Preço mínimo | `minPrice=100` |
| `maxPrice` | Preço máximo | `maxPrice=500` |
| `minStock` | Estoque mínimo | `minStock=5` |

Exemplo combinando filtros, paginação e ordenação:

```http
GET /products?name=teclado&category=electronics&minPrice=100&maxPrice=500&minStock=5&page=0&size=10&sort=price,asc
```

Os parâmetros de filtro são opcionais e podem ser combinados.

### Consultar produto por ID

```http
GET /products/1
```

### Atualizar parcialmente um produto

`PATCH /products/1`

Envie somente os campos que deseja alterar. Por exemplo:

```json
{
  "price": 219.90,
  "stock": 20
}
```

### Desativar produto

```http
PATCH /products/1/deactivate
```

Essa operação marca o produto como inativo, sem excluí-lo do banco de dados.

### Excluir produto

```http
DELETE /products/1
```

## Validações

O cadastro valida campos como nome, descrição, preço, categoria e estoque. Entre as regras configuradas estão:

- Nome obrigatório, com tamanho entre 3 e 100 caracteres;
- Descrição obrigatória, com até 500 caracteres;
- Preço obrigatório e maior que zero;
- Estoque obrigatório e maior ou igual a zero;
- Categoria limitada aos valores reconhecidos pela API.

Na atualização parcial, os campos podem ser omitidos; quando informados, as restrições de tamanho e valores numéricos são aplicadas conforme o DTO.

## Próximas melhorias

- Ampliar a suíte de testes unitários e de integração para serviços, mapeadores, filtros e endpoints;
- Documentar a API com OpenAPI/Swagger;
- Adicionar configuração de ambiente por variáveis externas, evitando credenciais fixas;
- Avaliar migrations de banco de dados com Flyway ou Liquibase;
- Adicionar pipeline de integração contínua e análise de cobertura de testes.

## Objetivo do projeto

Este projeto é um exercício de backend Java com foco em construção de APIs REST, separação de responsabilidades, persistência com JPA, validação, tratamento de erros e consultas dinâmicas.
