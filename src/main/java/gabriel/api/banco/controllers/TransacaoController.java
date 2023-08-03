package gabriel.api.banco.controllers;

import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.entities.FalhaLogTransacao;
import gabriel.api.banco.entities.Transacao;
import gabriel.api.banco.services.ContaService;
import gabriel.api.banco.services.FalhaLogTransacaoService;
import gabriel.api.banco.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private FalhaLogTransacaoService falhaLogTransacaoService;

    @GetMapping("/transacao/listar")
    @Operation(summary = "Retorne todas as transações")
    public List<Transacao> listarTransacaos() {
        return transacaoService.listarTransacoes();
    }

    @PostMapping("/transacao")
    @Operation(summary = "Criação de uma transação")
    @ResponseStatus(HttpStatus.CREATED)
    public Transacao criarTransacao(@RequestBody Transacao transacao) {
        try {
            Transacao.TipoTransacao tipoTransacao = transacao.getTipoTransacao();

            Transacao novaTransacao = transacaoService.criarTransacao(transacao);
            Conta conta = contaService.listarContaId((long) transacao.getConta().getContaID()).get();

            BigDecimal saldoFinal;
            if (tipoTransacao == Transacao.TipoTransacao.SAQUE) {
                saldoFinal = conta.getSaldoAtual().subtract(novaTransacao.getValorTransacao());
            } else if (tipoTransacao == Transacao.TipoTransacao.DEPOSITO) {
                saldoFinal = conta.getSaldoAtual().add(novaTransacao.getValorTransacao());
            } else if (tipoTransacao == Transacao.TipoTransacao.TRANSFERENCIA) {
                Conta contaDestino = contaService.listarContaId((long) transacao.getConta().getContaID()).orElseThrow(() -> new RuntimeException("Conta de destino não encontrada"));

                BigDecimal valorTransferencia = novaTransacao.getValorTransacao();
                if (conta.getSaldoAtual().compareTo(valorTransferencia) < 0) {
                    throw new RuntimeException("Saldo insuficiente para a transferência");
                }

                conta.setSaldoAtual(conta.getSaldoAtual().subtract(valorTransferencia));
                contaDestino.setSaldoAtual(contaDestino.getSaldoAtual().add(valorTransferencia));

                contaService.criarConta(conta);
                contaService.criarConta(contaDestino);

                saldoFinal = conta.getSaldoAtual();
            } else {
                throw new RuntimeException("Tipo de transação inválido");
            }

            conta.setSaldoAtual(saldoFinal);
            contaService.criarConta(conta);
            return novaTransacao;
        } catch (Exception e) {
            FalhaLogTransacao falhaLogTransacao = new FalhaLogTransacao();
            falhaLogTransacao.setTransacao(transacao);
            falhaLogTransacao.setTipoErro("Erro na transação: " + e.getMessage());
            falhaLogTransacao.setTimeStamp(new Date());
            falhaLogTransacaoService.salvarFalhaLogTransacao(falhaLogTransacao);
            throw e;
        }
    }



}
