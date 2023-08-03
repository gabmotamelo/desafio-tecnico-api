package gabriel.api.banco.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gabriel.api.banco.entities.Cliente;

public class ClienteUtilsTest {
    public static Cliente criarClienteExemplo() {
        return Cliente.builder()
                .nome("Exemplo")
                .endereco("Rua Exemplo, 123")
                .cep("12345-678")
                .telefone("11111111111")
                .email("exemplo@email.com")
                .cidade("Cidade Exemplo")
                .estado("Estado Exemplo")
                .cpf("12345678901")
                .rg("123456789")
                .build();
    }

    public static String toJson(Cliente cliente) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(cliente);
    }
}
