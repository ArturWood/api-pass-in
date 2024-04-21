<img src="https://github.com/ArturWood/games-list/assets/111249818/434c56b3-9dc9-412a-91f7-2edc3f389c14" width=300px alt="Java Logo" />
<img src="https://github.com/ArturWood/games-list/assets/111249818/d8539fd2-938e-4126-b3d4-7236a1ffdbef" width=500px alt="SpringFramework Logo" />

# Pass In - Aplicação Java para gerenciar eventos e participantes

### Projeto back-end desenvolvido utilizando Java 17 com Spring Framework

API REST responsavel por realizar operações de buscas e escritas em banco de dados local HSQLDB (HyperSQL DataBase) e retornar os dados em JSON.

Ela oferece endpoints para criar, obter detalhes e listar eventos, bem como registrar participantes em eventos e gerar crachás para os participantes.

![postman](https://github.com/ArturWood/api-pass-in/assets/111249818/be06c875-43e5-4947-9f8b-6e6c6a4cec97)

## Pré-requisitos

- Java Development Kit (JDK) versão 17
- IDE Java (como Eclipse ou IntelliJ) ou um editor de texto para escrever o código
- Fazer download das dependências e plugins utilizando maven
- Postman (opcional, para testar os endpoints localmente)

## Como executar o projeto

### Back-end
Pré-requisitos: Java JDK 17

```bash
# clonar repositório
git clone https://github.com/ArturWood/api-pass-in.git

# entrar na pasta do projeto
cd api-pass-in

# executar o projeto
./mvnw spring-boot:run
```

## Endpoints

A aplicação expõe os seguintes endpoints:

- `GET /events/{eventId}`: Recupera os detalhes de um evento específico com base no ID do evento.
- `POST /events`: Cria um novo evento com base nos dados fornecidos no corpo da solicitação.
- `GET /events/{eventId}/attendees`: Lista os participantes de um evento específico com base no ID do evento.
- `POST /events/{eventId}/attendees`: Registra um novo participante em um evento específico com base no ID do evento e nos dados fornecidos no corpo da solicitação.
- `GET /attendees/{attendeeId}/badge`: Recupera o crachá de um participante com base no ID do participante.
- `POST /attendees/{attendeeId}/check-in`: Registra o check-in de um participante com base no ID do participante.

## Estrutura do Projeto

O projeto possui a seguinte estrutura de arquivos:

```bash
├───src                                          
│   ├───main                                     
│   │   ├───java                                 
│   │   │   └───com                              
│   │   │       └───dev
│   │   │           └───api.pass.in
│   │   │               ├───controller
│   │   │               ├───dto
│   │   │               ├───model
│   │   │               ├───infra
│   │   │               ├───repository
│   │   │               └───service
│   │   └───resources
│   │       ├───static
│   │       └───templates
└── .gitignore
└── api-pass-in.postman_collection.json
└── pom.xml
```

- O pacote `resources` contém o arquivo `application.properties` que configura o ambiente da aplicação, e a configuração do banco de dados no `application-test.properties`.
- O pacote `controller` contém as classes que definem os endpoints da API.
- O pacote `service` contém as classes responsáveis por acesso ao BD e realizar a lógica de negócio.
- O pacote `infra` contém a classe `ExceptionEntityHandler` responsavel por lidar com as exceptions lançadas pelo controller ou service.
- O pacote `model` contém as classes que representam um evento ou participante e seu mapeamento no BD.
- O pacote `repository` contém as interfaces que definem operações de acesso a dados para as entidades.
- Na source do projeto temos o arquivo `.gitignore` que especifica os arquivos e pastas que devem ser ignorados pelo controle de versão do Git
- O arquivo `api-pass-in.postman_collection` para consultar e testar os endpoints na API.
- O arquivo `pom.xml` para download das dependencias necessarias para o projeto usando maven.

## Documentação

No projeto foi utilizado o HSQLDB para desenvolvimento local;<br>
Foi adicionado a dependência `springdoc` para facilitar a documentação e visualização dos endpoints (acessar rodando localmente);<br>
Alem das dependencias para desenvolvimento com Spring Framework - Web, Bean, JPA;<br>
Links para uso e documentação:

https://hsqldb.org/web/hsqlDocsFrame.html<br>
https://spring.io/projects/spring-data-jpa<br>
https://docs.spring.io/spring-boot/docs/current/reference/html/web.html<br>
http://localhost:8080/swagger-ui/index.html

![swagger](https://github.com/ArturWood/api-pass-in/assets/111249818/5e621920-8166-4145-a965-4839f035dfed)
