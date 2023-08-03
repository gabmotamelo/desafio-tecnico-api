package gabriel.api.banco.utils;

import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.entities.TipoTransacao;
import gabriel.api.banco.entities.Transacao;

import java.math.BigDecimal;
import java.util.Date;

public class TransacaoUtilsTest {

    public static Transacao criarTransacaoExemplo() {
        Conta conta = ContaUtilsTest.criarContaExemplo();

        return Transacao.builder()
                .tipoTransacao(Transacao.TipoTransacao.SAQUE)
                .dataTransacao(new Date())
                .valorTransacao(BigDecimal.valueOf(200))
                .novoSaldo(conta.getSaldoAtual().subtract(BigDecimal.valueOf(200)))
                .conta(conta)
                .build();
    }

    public static Transacao criarTransacaoDeposito() {
        Conta conta = ContaUtilsTest.criarContaExemplo();

        return Transacao.builder()
                .tipoTransacao(Transacao.TipoTransacao.DEPOSITO)
                .dataTransacao(new Date())
                .valorTransacao(BigDecimal.valueOf(500))
                .novoSaldo(conta.getSaldoAtual().add(BigDecimal.valueOf(500)))
                .conta(conta)
                .build();
    }

    public static Transacao criarTransacaoTransferencia() {
        Conta contaOrigem = ContaUtilsTest.criarContaExemplo();
        Conta contaDestino = ContaUtilsTest.criarContaExemplo();

        BigDecimal valorTransferencia = BigDecimal.valueOf(300);
        BigDecimal novoSaldoContaOrigem = contaOrigem.getSaldoAtual().subtract(valorTransferencia);
        BigDecimal novoSaldoContaDestino = contaDestino.getSaldoAtual().add(valorTransferencia);

        return Transacao.builder()
                .tipoTransacao(Transacao.TipoTransacao.TRANSFERENCIA)
                .dataTransacao(new Date())
                .valorTransacao(valorTransferencia)
                .novoSaldo(novoSaldoContaOrigem)
                .conta(contaOrigem)
                .conta(contaDestino)
                .build();
    }
}
