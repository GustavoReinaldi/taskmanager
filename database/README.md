# Contexto

Neste diretório você pode encontrar todas as queries necessárias para preparar o banco de dados da API TaskManager. Esta API foi criada para cumprir com o teste de conhecimento proposto pela empresa Stefanini.

## Estrutura de diretórios

No diretório query você encontrará 2 sub-diretórios 'sql' e 'rolllback'. Ambos possuem arquivos .sql que servem para seus devidos propósitos. 

Os que estão no repositório 'sql' são todas as queries necessárias para criação do banco e tabelas. Já os que estão no repositório 'rollback' servem para desfazer essa criação de bancos, seus nomes são equivalentes aos scripts presentes na pasta 'sql', pois cada um deles se refere à um dos scripts da pasta 'sql', com diferença, claro, do sufixo "_ROLLBACK". 

## Padrão de nomenclatura de queries

Os arquivos possuem padrão de nomenclatura para possível versionamento em ferramentas como "FLYWAY". O padrão é "VYYYYMMDDHHMM__NN_TTTTTTTTTT.sql":
- V : Fixo, se refere à palavra "VERSION"
- Y : Ano de criação (4 dígitos)
- M : Mês de criação (2 dígitos)
- D : Dia de criação (2 dígitos)
- H : Hora de criação (2 dígitos)
- M : Minuto de criação (2 dígitos)
- N : Número sequencia que indica a ordem que o script deve ser executado (2 dígitos)
- T : Descrição do que cada um dos scripts fazem (N dígitos)

## Docker compose

Este ```Docker Compose``` é uma alternativa à instalação do SQL Server na sua máquina. 

> [!WARNING]
>
> Se faz necessária a instalação do WSL + Docker na sua máquina. Consulte o site oficial do [Docker](https://docs.docker.com/desktop/) para obter mais instruções.

Abra o Powersheel da sua máquina e execute o comando:
```console
docker-compose -f "compose-sql-server.yml" up -d
``` 

Após isso o seu SQL Server estará rodando na porta 1433 e você poderá acessá-lo com as seguintes credenciais:
- usuário: sa
- senha: Str0ngP@ss
- database: master