# Introdução

Esta API RESTful, desenvolvida em Spring Boot 3.4.1 e Java 17, é destinada ao gerenciamento de tarefas. Ela permite criar, ler, atualizar e deletar tarefas. 

Este projeto foi desenvolvido como avaliação para a **Stefanini Consultoria e Assessoria em Informática S/A**, demonstrando proficiência em desenvolvimento back-end com Spring Boot e Java.

## Funcionalidades
### CRUD de Tarefas:
- Criação: Adicione novas tarefas com título, descrição e data de vencimento.
- Leitura: Liste todas as tarefas ou busque uma tarefa específica por ID.
- Atualização: Modifique o título, descrição e data de vencimento de uma tarefa existente.
- Deleção: Remova uma tarefa específica.

## Tecnologias Utilizadas
- **Spring Boot 3.4.1**: Framework para desenvolvimento de aplicações Java. 
- **Java 17:** Linguagem de programação utilizada. 
- **JPA e Hibernate:** Para persistência de dados em um banco de dados relacional. 
- **Lombok:** Para reduzir a quantidade de código boilerplate. 
- **Swagger:** Para geração de documentação interativa da API. 
- **JUnit:** Para testes unitários.

## Pré-requisitos
* Java Development Kit (JDK) 17: Instale o JDK 17 em sua máquina.
* Gerenciador de pacotes: Gradle.
* Banco de dados relacional: SQL Server.
* IDE como IntelliJ IDEA, Eclipse ou linha de comando.

### Configuração e Execução
Clone o repositório:
```Bash
git clone https://github.com/GustavoReinaldi/taskmanager.git
```

### Configure o banco de dados:
1. Crie um banco de dados: Utilize os scripts .sql contidos na pasta "banco de dados".
2. Após a execução dos configure as variáveis de ambiente do projeto ```DBURL```, ```DBUSR``` e ```DBPWD``` para conexão com o banco de dados. 

#### Exemplo de variáveis:

* DBURL=jdbc:sqlserver://localhost:1433\;databaseName=taskmanager\;encrypt=false\;trustServerCertificate=true;
* DBPWD=Str0ngP@ss;
* DBUSR=SA

Essas variáveis de ambiente facilitam deploy em outros diversos ambientes.

## Execute a aplicação:

### Gradle:
```Bash
gradle bootRun -Dspring.config.location=classpath:/application.properties
```

### Teste as funcionalidades

Você pode verificar se tudo está rodando da maneira correta pelas URLs:
- http://localhost:8080/actuator/health - Ver se a API está rodando 
- http://localhost:8080/actuator/info - Ver informações da aplicação
- http://localhost:8080/swagger-ui/index.html/ - Consultar toda a documentação da API

## Documentação da API
Documentação: A documentação interativa da API, gerada pelo Swagger, está disponível em http://localhost:8080/swagger-ui/index.html/ (ou a porta que você configurou). 

Nela, você encontrará informações detalhadas sobre os endpoints, parâmetros, respostas e exemplos de requisições.

---

> [!TIP]
> Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.
>