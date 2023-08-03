
<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white">

  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white">

  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white">

  <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white">

  <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white">

</p>

<h1 align="center">
    🏦 Api Sistema Bancário 💵
</h1>

<h4 align="center"> 
	🚧  ATM 🏧 BANCO 🚧
</h4>

## 💻 Sobre o projeto

📟 ATM Api - é uma api do sistema de um caixa eletrônico com algumas funcionalidades de contas digitais.


- Projeto desenvolvido para o desafio técnico do [Banco - BTG Pactual](https://www.btgpactual.com/).


---

## ⚙️ Funcionalidades

- [x] CONTA:
  - Tipo - "Jurídica" ou "Física"
  - Status - "Ativa" ou "Inativa"
- [x] CLIENTE
- [x] TRANSAÇÕES:
  - Depósito
  - Saque
  - Transferência
  - Log de Erros

---

## 💾 Banco de Dados - PostgreSQL

- Diagrama Entidade Relacionamento:

  ![DER](https://github.com/gabmotamelo/desafio-tecnico-api/assets/88755473/d783b3b9-f63a-4ad1-9285-c615448a29d4)

---

- Modelo Entidade Relacionamento:

  ![MER](https://github.com/gabmotamelo/desafio-tecnico-api/assets/88755473/e511708f-21ea-4315-ad9c-c38084c1d76b)

---

## 📤 Scripts DDL:

```sql

CREATE TABLE TipoConta (
                         TipoContaID SERIAL PRIMARY KEY,
                         TipoContaDescricao VARCHAR(30) NOT NULL,
                         CONSTRAINT check_tipoconta_limit CHECK (TipoContaID <= 2)
);


CREATE TABLE TipoStatusConta (
                               StatusContaID SERIAL PRIMARY KEY,
                               StatusContaDescricao VARCHAR(30) NOT NULL
                                 CONSTRAINT check_tipostatusconta_limit CHECK (StatusContaID <= 2)
);

CREATE TABLE Conta (
                     ContaID SERIAL PRIMARY KEY,
                     SaldoAtual DECIMAL,
                     TipoContaID INT NOT NULL,
                     StatusContaID INT NOT NULL,
                     FOREIGN KEY (TipoContaID) REFERENCES TipoConta(TipoContaID),
                     FOREIGN KEY (StatusContaID) REFERENCES TipoStatusConta(StatusContaID)
);

CREATE TABLE TipoTransacao (
                             TipoTransacaoID SERIAL PRIMARY KEY,
                             Descricao VARCHAR(30) NOT NULL
                               CONSTRAINT check_tipotransacao_limit CHECK (TipoTransacaoID <= 3)
);

CREATE TABLE Cliente (
                       ClienteID SERIAL PRIMARY KEY,
                       Nome VARCHAR(60) NOT NULL,
                       Endereco VARCHAR(60),
                       Cep CHAR(10),
                       Telefone CHAR(11),
                       Email VARCHAR(40),
                       Cidade VARCHAR(20),
                       Estado VARCHAR(40),
                       Cpf CHAR(11),
                       Rg CHAR(9)
);

CREATE TABLE Cliente_Conta (
                             ClienteContaID SERIAL PRIMARY KEY,
                             ClienteID INT NOT NULL,
                             ContaID INT NOT NULL,
                             FOREIGN KEY (ClienteID) REFERENCES Cliente(ClienteID),
                             FOREIGN KEY (ContaID) REFERENCES Conta(ContaID)
);

CREATE TABLE Transacao (
                         TransacaoID SERIAL PRIMARY KEY,
                         DataTransacao TIMESTAMP,
                         ValorTransacao DECIMAL,
                         NovoSaldo DECIMAL,
                         TipoTransacaoID INT NOT NULL,
                         ContaID INT NOT NULL,
                         ClienteID INT NOT NULL,
                         FOREIGN KEY (TipoTransacaoID) REFERENCES TipoTransacao(TipoTransacaoID),
                         FOREIGN KEY (ContaID) REFERENCES Conta(ContaID),
                         FOREIGN KEY (ClienteID) REFERENCES Cliente(ClienteID)
);

CREATE TABLE FalhaLogTransacao (
                                 TransacaoID INT PRIMARY KEY REFERENCES Transacao(TransacaoID),
                                 TipoErro VARCHAR(255),
                                 TimeStamp TIMESTAMP
);


```
> Veja o arquivo  [SystemDDL.sql](https://github.com/gabmotamelo/desafio-tecnico-api/blob/main/src/main/resources/db/SystemDDL.sql)

## 📤 Scripts DDL:
<br><br>

```sql
-- Inserir tipos de conta
INSERT INTO TipoConta (TipoContaDescricao) VALUES ('Conta Jurídica');
INSERT INTO TipoConta (TipoContaDescricao) VALUES ('Conta Física');

-- Inserir tipos de transação
INSERT INTO TipoTransacao (Descricao) VALUES ('Depósito');
INSERT INTO TipoTransacao (Descricao) VALUES ('Saque');
INSERT INTO TipoTransacao (Descricao) VALUES ('Transferência');

-- Inserir tipos de status de conta
INSERT INTO TipoStatusConta (StatusContaDescricao) VALUES ('Ativa');
INSERT INTO TipoStatusConta (StatusContaDescricao) VALUES ('Inativa');

-- Inserir clientes
INSERT INTO Cliente (Nome, Endereco, Cep, Telefone, Email, Cidade, Estado, Cpf, Rg)
VALUES ('João da Silva', 'Rua A, 123', '12345-678', '11111111111', 'joao@email.com', 'Cidade A', 'Estado A', '12345678901', '123456789');

INSERT INTO Cliente (Nome, Endereco, Cep, Telefone, Email, Cidade, Estado, Cpf, Rg)
VALUES ('Maria', 'Avenida B, 456', '98765-432', '22222222222', 'maria@email.com', 'Cidade B', 'Estado B', '98765432109', '987654321');

-- Inserir contas
INSERT INTO Conta (SaldoAtual, TipoContaID, StatusContaID)
VALUES (1000.00, 1, 1);

INSERT INTO Conta (SaldoAtual, TipoContaID, StatusContaID)
VALUES (5000.00, 2, 2);

-- Inserir cliente_conta (associação entre cliente e conta)
INSERT INTO Cliente_Conta (ClienteID, ContaID)
VALUES (1, 1);

INSERT INTO Cliente_Conta (ClienteID, ContaID)
VALUES (2, 2);


-- Inserir transações
INSERT INTO Transacao (DataTransacao, ValorTransacao, NovoSaldo, TipoTransacaoID, ContaID, ClienteID)
VALUES ('2023-08-02 10:00:00', 500.00, 1500.00, 1, 1, 1);
INSERT INTO Transacao (DataTransacao, ValorTransacao, NovoSaldo, TipoTransacaoID, ContaID, ClienteID)
VALUES ('2023-08-03 14:30:00', -200.00, 1300.00, 2, 1, 1);
INSERT INTO Transacao (DataTransacao, ValorTransacao, NovoSaldo, TipoTransacaoID, ContaID, ClienteID)
VALUES ('2023-08-04 11:45:00', 1000.00, 2300.00, 1, 2, 1);

-- Inserir falhas de log de transação
INSERT INTO FalhaLogTransacao (TransacaoID, TipoErro, TimeStamp)
VALUES (1, 'Erro A', '2023-08-02 10:15:00');
INSERT INTO FalhaLogTransacao (TransacaoID, TipoErro, TimeStamp)
VALUES (2, 'Erro B', '2023-08-03 14:45:00');

```
> Veja o arquivo  [SystemDML.sql](https://github.com/gabmotamelo/desafio-tecnico-api/blob/main/src/main/resources/db/SystemDML.sql)

---

## 📑 Swagger Documentation:
<br><br>
![doc](https://github.com/gabmotamelo/desafio-tecnico-api/assets/88755473/2e5455d9-45bb-4cc1-ab37-888dbe42145f)

-   **[Swagger Sistema Bancário API](http://localhost:8080/swagger-ui/index.html#/)**
    <br><br>
---

## 🛠 Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

-   **Java - 20**
-   **Spring Framework - 3.1.2**
-   **Lombok**
-   **PostgreSQL**
-   **Mockito - (testes)**
-   **Swagger - (documentação) 2.1.0**
-   **Maven**




## 🚀 Como executar o projeto


### Pré-requisitos

- Java Version : 20
- Maven

#### 🎲 Rodando o Backend

```bash

# Clone este repositório
$ git clone git@github.com:gabmotamelo/desafio-tecnico-api.git

# Acesse a pasta do projeto no terminal/cmd
$ cd api-banco-btg

# Vá para a pasta server
$ mvn spring-boot:run

# O servidor inciará na porta:8080 - acesse http://localhost:8080 

```

---

## 🦸 Autor

<a >
 <img style="border-radius: 50%;" src="https://github.com/gabmotamelo/desafio-tecnico-api/assets/88755473/b0e5085d-0fd0-4a4c-8c08-15c017f6e2d3" width="100px;" alt=""/>
 <br />
 <sub><b>Gabriel Mota Melo</b></sub></a> 
 <br/>
 <br/>


Feito por Gabriel 👋🏽 [Entre em contato - LinkedIn!](https://www.linkedin.com/in/gabriel-mota-melo/)
<a >
