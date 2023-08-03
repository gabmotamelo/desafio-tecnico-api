-- Inserir tipos de conta
INSERT INTO TipoConta (TipoContaDescricao) VALUES ('Conta Jurídica');
INSERT INTO TipoConta (TipoContaDescricao) VALUES ('Conta Física');

-- Inserir tipos de transaçã
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
