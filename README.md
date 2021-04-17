# Ufes Movie Database (UMDB)

UMDB é um software para que permite que usuários busquem pro informações de filmes e avaliem os mesmos. 

Para realizar o desenvolvimento dos mesmo, as seguintes tecnologias foram utilizadas:

* Servidor
	- Spring Boot
	- Java
	- MySQL

* Frontend
	- ReactJS

### Acesso da aplicação
Foi realizado deploy da aplicação no serviço Heroku. a aplicação final poder ser acessada [aqui](https://umdb-client.herokuapp.com/).
	
### Configuração de ambiente para execução local
A seguir são descritos como instalar as tecnologias necessárias para execução e desenvolvimento do servidor. Como ele é baseado no Spring Boot e Java, é necessário a instalação do JDK do Java 8. Após isso será descrito como configurar a aplicação web baseada em ReactJS .Outro ponto importante é que o tutorial é realizado para usuários Linux utilizando o `apt-get`.


### Instalando o OpenJDK 8

1. Incluir o repositório do OpenJDK 8 no `apt-get`:

`sudo add-apt-repository ppa:openjdk-r/ppa`

2. Atualizar o `apt-get`:

`sudo apt-get update`

3. Instalar o software:

`sudo apt-get install openjdk-8-jdk`

4. Se você tiver mais de uma versão do Java instalado, execute os comandos para definir o padrão:

```
sudo update-alternatives --config java
sudo update-alternatives --config javac
```

5. Finalmente confira a versão Java atual:
`java -version`

### Instalando Maven

Maven é o gerenciador de dependências utilizado para o Java/Spring Boot. Sua instalação é bem simples:

`sudo apt-get install maven`

### Instalação do MySQL
Para instalar o MySQL execute: `sudo apt-get install mysql-server`

**Nota:** Se o MySQL não apresentar as configurações no momento da instalação, siga [este tutorial](https://medium.com/@leandroembu/como-instalar-o-mysql-no-ubuntu-18-04-2ef208a728fa) para realizar a configuração.


# Executando o projeto

Para executar o projeto pela primeira vez você deve seguir os seguintes passos:

Na sequencia, crieu um arquivo de nome `application.properties` no diretório `server/src/main/resources/` com as seguintes instruções:

```
# Configuracoes gerais:
spring.datasource.username={db_user}
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
spring.servlet.multipart.max-file-size=100MB
spring.servlet.multipart.max-request-size=200MB
spring.data.rest.base-path=/api
server.servlet.session.timeout=300m
security.basic.enabled=false

# Configs que diferem na producao e no desenvolvimento
spring.datasource.url=jdbc:mysql://localhost/umdb?useTimezone=true&serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.password={db_password}
server.port=8080

# Configuracoes das datas:
spring.jackson.date-format=yyyy-MM-dd
spring.jackson.time-zone=Brazil/East

# Configuracoes do CORS
#management.endpoints.web.cors.allow-credentials=* # Whether credentials are supported. When not set, credentials are not supported.
management.endpoints.web.cors.allowed-headers=* # Comma-separated list of headers to allow in a request. '*' allows all headers.
management.endpoints.web.cors.allowed-methods=* # Comma-separated list of methods to allow. '*' allows all methods. When not set, defaults to GET.
management.endpoints.web.cors.allowed-origins=* # Comma-separated list of origins to allow. '*' allows all origins. When not set, CORS support is disabled.
management.endpoints.web.cors.exposed-headers=* # Comma-separated list of headers to include in a response.
management.endpoints.web.cors.max-age=3600s
```

Crie um banco de dados chamado `umdb` e altere seu usuário e senha do banco de dados alterando `spring.datasource.username` e `spring.datasource.password` no `application.properties`. 

2. Abra um terminal na pasta raíz do servidor e execute o comando: `mvn spring-boot:run`. Se tudo ocorreu com sucesso, o Spring Boot (via Tomcat) subirá um servidor no `localhost:8080`. Basta acessar o endereço no browser.

# Instruções para consultas no banco de dados
As consultas no banco de dados são realizadas utilizando o Spring Data JPA. Todas as classes que realizam essa comunicação estão no pacote `repository`. Esse mesmo pacote é responsável pela API (descrita abaixo).
Para obter mais informações do seu funcionamento basta acessar [a documentação do Spring Data](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)

# Instruções para usar a API
Toda API foi construída utilizando o Spring Data Rest sob a rota `/api/[nivel_acesso]/[entidade]`. Para entender melhor o funcionamento do framework você deve acessar os seguintes links:
* [Iniciando com Spring Data Rest](https://spring.io/guides/gs/accessing-data-rest/)
* [Documentação do Spring Data Rest](https://docs.spring.io/spring-data/rest/docs/current/reference/html/)


Como já descrito, o frontend é desenvolvido em ReactJS. Portanto, precisamos instalar o Nodejs, o gerenciador de dependências npm.


### Nodejs

Para instalar o Nodejs execute o comando: `$ curl -sL https://deb.nodesource.com/setup_12.x | sudo bash -` 
O final do comando `setup_12.x` siginifica que é a versão 12.x do Node.

Após a execução do comando, verifique se foi instalado corretamente digitando no terminal:
`node --version`


### NPM

O comando anterior já instala o NPM. Você pode checar se está tudo certo executando o seguinte comando: `npm --version`

# Executando o frontend

Para executar o frontend em standalone, primeiramente abra a pasta do projeto e execute o comando `npm install`. Com isso, todas as dependências serão baixadas. 


Baixada as depêndencias, ao executar `npm start` o projeto será servido no endereço `localhost:3000`.


### Mais informações
* [Spring-boot](https://spring.io/projects/spring-boot)
* [Java](https://www.java.com/en/download/help/download_options.xml)
* [Maven](https://maven.apache.org/)
* [MySQL](https://www.mysql.com/downloads/)

* [Nodejs](https://nodejs.org/en/)
* [NPM](https://docs.npmjs.com/cli/install)
* [ReactJS](https://pt-br.reactjs.org/)
