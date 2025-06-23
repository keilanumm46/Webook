# 📚 Literalura - Catálogo de Livros

Aplicação Java que consome a API pública do [Gutendex](https://gutendex.com/), armazena livros e autores em um banco PostgreSQL (via Docker), e permite ao usuário consultar e registrar informações por meio de um menu interativo no terminal.

---

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL (via Docker)
- Lombok
- Gutendex API
- Maven
- Docker e Docker Compose

---

## 🎯 Objetivo

Este projeto tem como objetivo consolidar conhecimentos em:

- Consumo de APIs REST com Java
- Persistência de dados com JPA/Hibernate
- Organização de código em camadas (DTOs, Services, Repositories)
- Utilização de banco de dados com Docker
- Boas práticas de modelagem de entidades e relacionamentos

---

## 🧠 Funcionalidades

O sistema oferece um menu interativo com as seguintes opções:

1. 📖 Buscar livro pelo título
2. 📚 Listar livros registrados
3. ✍️ Listar autores registrados
4. 🌍 Listar autores vivos em um determinado ano
5. 🔤 Listar livros por idioma
6. ❌ Sair do programa

---

## 🐳 Docker + PostgreSQL

O banco de dados é executado via Docker.

### `docker-compose.yml`

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:16
    container_name: gutendex_postgres
    environment:
      POSTGRES_USER: usuario
      POSTGRES_PASSWORD: senha
      POSTGRES_DB: literalura
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
    networks:
      - literalura-net

networks:
  literalura-net:

volumes:
  pgdata:
```

---

## ⚙️ Configuração da Aplicação

### `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=usuario
spring.datasource.password=senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🧱 Estrutura do Projeto

```
literalura/
├── src/
│   └── main/
│       ├── java/com/alura/literalura/
│       │   ├── dto/             # Data Transfer Objects
│       │   ├── model/           # Entidades: Livro, Autor
│       │   ├── repository/      # Interfaces JpaRepository
│       │   ├── service/         # Serviços de negócio
│       │   └── principal/       # Classe Principal com menu interativo
│       └── resources/
│           └── application.properties
├── docker-compose.yml
└── pom.xml
```

---

## ▶️ Como Executar

### Pré-requisitos

- Java 17+
- Docker + Docker Compose
- Maven

### Passo a passo

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/literalura.git
cd literalura
```

2. Suba o banco de dados:
```bash
docker-compose up -d
```

3. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

4. Interaja com o menu pelo terminal 🎉

---

## 💡 Exemplos de Uso

- Buscar pelo título: `Pride and Prejudice`
- Listar autores vivos no ano de 1900
- Filtrar livros por idioma: `en`, `pt`, etc.

---

## ✨ Melhorias Futuras

- Interface gráfica Web com Spring + React
- Filtros por gênero e temas
- Ranking de livros por autor
- Paginação e ordenação dos resultados

---

## 👩‍💻 Desenvolvedora

**Keila Nunes**  
Desenvolvedora Java em formação  
📫 [LinkedIn](https://www.linkedin.com/in/keila-nunes-devux/?trk=public_profile-settings_edit-profile-content&originalSubdomain=br)

---

## 📜 Licença

Este projeto está sob a licença MIT.  
Feito com ☕ e dedicação.