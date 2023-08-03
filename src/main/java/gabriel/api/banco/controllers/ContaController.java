package gabriel.api.banco.controllers;


import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.entities.Transacao;
import gabriel.api.banco.services.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/conta")
    @Operation(summary = "Criação da Conta.")
    public Conta criarConta(@RequestBody Conta conta){
        return contaService.criarConta(conta);
    }

    @PutMapping("/conta")
    @Operation(summary = "Atualizar dados da Conta.")
    public Conta atualizarConta(@RequestBody Conta conta) {
        return contaService.criarConta(conta);
    }

    @GetMapping("/conta")
    @Operation(summary = "Retorne as contas.")
    public List<Conta> listarContas(){
        return contaService.listarContas();
    }

    @GetMapping("/conta/{contaId}")
    @Operation(summary = "Retorne a conta pelo Id.")
    public Optional<Conta> listarContas(@PathVariable("contaId") Long numeroConta){
        return contaService.listarContaId(numeroConta);
    }
    @GetMapping("/conta/{id}/transacoes")
    @Operation(summary = "Retorne todas as transações pelo ID da Conta")
    public List<Transacao> listarContaTransacoes(@PathVariable("id") Long id){
        return contaService.listarContaId(id).get().getTransacoes();
    }

}
