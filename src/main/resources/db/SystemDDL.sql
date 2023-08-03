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
