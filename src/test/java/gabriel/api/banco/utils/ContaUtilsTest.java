package gabriel.api.banco.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gabriel.api.banco.entities.Cliente;
import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.entities.TipoConta;
import gabriel.api.banco.entities.TipoStatusConta;

import java.math.BigDecimal;

public class ContaUtilsTest {
    public static Conta criarContaExemplo() {
        return Conta.builder()
                .tipoConta(TipoConta.builder().tipoContaID(1L).tipoContaDescricao("Conta Corrente").build())
                .statusConta(TipoStatusConta.builder().statusContaID(1L).statusContaDescricao("Ativa").build())
                .saldoAtual(BigDecimal.valueOf(1000))
                .cliente(ClienteUtilsTest.criarClienteExemplo())
                .build();
    }

    public static String toJson(Conta conta) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(conta);
    }
}
