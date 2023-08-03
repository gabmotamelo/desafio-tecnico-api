package gabriel.api.banco.controller;

import gabriel.api.banco.controllers.ContaController;
import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.services.ContaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ContaControllerTest {

    @Mock
    private ContaService contaService;

    @InjectMocks
    private ContaController contaController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contaController).build();
    }

    @Test
    public void testListarContas() throws Exception {
        List<Conta> contas = new ArrayList<>();
        contas.add(new Conta(1, null, null, new BigDecimal("1000.00"), null, null));
        contas.add(new Conta(2, null, null, new BigDecimal("5000.00"), null, null));

        when(contaService.listarContas()).thenReturn(contas);

        mockMvc.perform(get("/conta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testListarContaPorId() throws Exception {
        Conta conta = new Conta(1, null, null, new BigDecimal("1500.00"), null, null);

        when(contaService.listarContaId(1L)).thenReturn(Optional.of(conta));

        mockMvc.perform(get("/conta/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.saldoAtual").value("1500.0"));
    }

    @Test
    public void testCriarConta() throws Exception {
        Conta novaConta = new Conta(3, null, null, new BigDecimal("2000.00"), null, null);

        mockMvc.perform(post("/conta")
                        .contentType("application/json")
                        .content("{\"saldoAtual\": \"2000.00\"}"))
                .andExpect(status().isOk());

        verify(contaService, times(1)).criarConta(any());
    }

    // Teste outros métodos e cenários conforme necessário
}
