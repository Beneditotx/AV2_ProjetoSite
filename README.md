# Subnautica Wiki GX

Projeto Spring Boot para catálogo colaborativo de itens de Subnautica feito por Benedito Teixeira e Luis Felipe Broad.

## Executar

É necessário ter Java 17 ou superior instalado. No Windows, execute `mvnw.cmd spring-boot:run` na pasta do projeto e abra `http://localhost:8080`.

O banco H2 em arquivo fica em `data/subnautica`. As migrations Flyway criam as tabelas e carregam o catálogo original do protótipo na primeira execução. O console H2 fica em `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:file:./data/subnautica`, usuário `sa`, senha em branco).

## Catálogo

Em **Itens**, é possível pesquisar, filtrar por categoria, cadastrar, consultar detalhes, editar e excluir registros. O serviço impede nomes repetidos dentro da mesma categoria. O catálogo começa com 160 itens e 15 categorias reaproveitados do protótipo.

## Verificação

Execute `mvnw.cmd test` para compilar e executar os testes.
