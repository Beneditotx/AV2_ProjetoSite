# Subnautica Wiki GX

Projeto Spring Boot para catálogo colaborativo de itens de Subnautica desenvolvido por Benedito Teixeira e Luis Felipe Broad.

---

## Modos de Execução

Você pode executar o projeto de duas formas: utilizando **Docker** (recomendado, sem necessidade de configurar Java ou banco localmente) ou **Localmente** (ideal para desenvolvimento na IDE).

---

### Modo 1: Executando com Docker (Recomendado)

**Pré-requisitos:**
* [Docker Desktop](https://www.docker.com/) instalado e em execução.

#### Passo a passo:

1. Clone o repositório e acesse a pasta raiz:
   ```bash
   git clone <URL_DO_SEU_REPOSITORIO>
   cd AV2_ProjetoSite
   ```

2. Suba os contêineres da aplicação e do banco PostgreSQL:
   ```bash
   docker compose up --build -d
   ```

3. Acompanhe a inicialização dos serviços e as migrações do Flyway:
   ```bash
   docker compose logs -f subnautica-app
   ```

4. Acesse a aplicação no navegador:
   ```text
   http://localhost:8080
   ```

5. Para parar a execução dos contêineres:
   ```bash
   docker compose down
   ```
   *(Os dados cadastrados são mantidos de forma persistente através do volume gerenciado pelo Docker).*

---

### Modo 2: Executando Localmente (Desenvolvimento / IDE)

**Pré-requisitos:**
* Java 17 ou superior instalado.
* Instância do PostgreSQL rodando na porta `5432` com a base `subnautica_db` criada, **ou** suba apenas o contêiner do banco com:
  ```bash
  docker compose up -d postgres-db
  ```

#### Passo a passo:

1. No terminal da pasta do projeto, execute:
   * **Windows:**
     ```bash
     .\mvnw.cmd spring-boot:run
     ```
   * **Linux/macOS:**
     ```bash
     ./mvnw spring-boot:run
     ```

2. Abra o navegador em:
   ```text
   http://localhost:8080
   ```

---

## Banco de Dados e Migrations

O projeto utiliza o **PostgreSQL 16**. O gerenciamento do esquema e o povoamento inicial de dados são controlados automaticamente pelo **Flyway**:

* Na primeira execução, o Flyway cria as tabelas e carrega o catálogo inicial com 160 itens e 15 categorias.
* Parâmetros de conexão padrão configurados no ambiente:
  * **Database:** `subnautica_db`
  * **User:** `subnautica_user`
  * **Password:** `subnautica_pass`
  * **Porta:** `5432`

---

## Funcionalidades do Catálogo

Na seção de **Itens**, é possível:
* Pesquisar registros por texto.
* Filtrar por categoria.
* Cadastrar novos itens (o serviço impede duplicidade de nomes dentro da mesma categoria).
* Consultar detalhes completos, editar e excluir registros existentes.

---

## Testes Automatizados

Para compilar o código e rodar a suíte de testes unitários:

* **Windows:**
  ```bash
  .\mvnw.cmd test
  ```
* **Linux/macOS:**
  ```bash
  ./mvnw test
  ```