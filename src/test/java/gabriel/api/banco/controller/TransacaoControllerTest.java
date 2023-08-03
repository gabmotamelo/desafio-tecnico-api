package gabriel.api.banco.controller;

import gabriel.api.banco.controllers.TransacaoController;
import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.entities.Transacao;
import gabriel.api.banco.services.ContaService;
import gabriel.api.banco.services.FalhaLogTransacaoService;
import gabriel.api.banco.services.TransacaoService;
import gabriel.api.banco.utils.ContaUtilsTest;
import gabriel.api.banco.utils.TransacaoUtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TransacaoControllerTest {

    @Mock
    private TransacaoService transacaoService;

    @Mock
    private ContaService contaService;

    @Mock
    private FalhaLogTransacaoService falhaLogTransacaoService;

    @InjectMocks
    private TransacaoController transacaoController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        Mockito.reset(transacaoService, contaService, falhaLogTransacaoService);
    }

    @Test
    public void testCriarTransacao_Saque_Success() throws Exception {
        Conta conta = ContaUtilsTest.criarContaExemplo();
        Transacao transacao = TransacaoUtilsTest.criarTransacaoExemplo();

        when(transacaoService.criarTransacao(any(Transacao.class))).thenReturn(transacao);
        when(contaService.listarContaId(1L)).thenReturn(java.util.Optional.of(conta));

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"tipoTransacao\": \"SAQUE\", \"valorTransacao\": 200, \"conta\": { \"contaID\": 1 } }"))
                .andExpect(status().isCreated());

        verify(transacaoService, times(1)).criarTransacao(any(Transacao.class));
        verify(contaService, times(1)).listarContaId(1L);
        verify(contaService, times(1)).criarConta(any(Conta.class));
        verifyNoMoreInteractions(transacaoService, contaService, falhaLogTransacaoService);
    }

    @Test
    public void testCriarTransacao_Deposito_Success() throws Exception {
        Conta conta = ContaUtilsTest.criarContaExemplo();
        Transacao transacao = TransacaoUtilsTest.criarTransacaoDeposito();

        when(transacaoService.criarTransacao(any(Transacao.class))).thenReturn(transacao);
        when(contaService.listarContaId(1L)).thenReturn(java.util.Optional.of(conta));

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"tipoTransacao\": \"DEPOSITO\", \"valorTransacao\": 500, \"conta\": { \"contaID\": 1 } }"))
                .andExpect(status().isCreated());

        verify(transacaoService, times(1)).criarTransacao(any(Transacao.class));
        verify(contaService, times(1)).listarContaId(1L);
        verify(contaService, times(1)).criarConta(any(Conta.class));
        verifyNoMoreInteractions(transacaoService, contaService, falhaLogTransacaoService);
    }

    @Test
    public void testCriarTransacao_Transferencia_Success() throws Exception {
        Conta contaOrigem = ContaUtilsTest.criarContaExemplo();
        Conta contaDestino = ContaUtilsTest.criarContaExemplo();

        Transacao transacao = TransacaoUtilsTest.criarTransacaoTransferencia();

        when(transacaoService.criarTransacao(any(Transacao.class))).thenReturn(transacao);
        when(contaService.listarContaId(1L)).thenReturn(java.util.Optional.of(contaOrigem));
        when(contaService.listarContaId(2L)).thenReturn(java.util.Optional.of(contaDestino));

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"tipoTransacao\": \"TRANSFERENCIA\", \"valorTransacao\": 300, \"conta\": { \"contaID\": 1 }, \"contaDestino\": { \"contaID\": 2 } }"))
                .andExpect(status().isCreated());

        verify(transacaoService, times(1)).criarTransacao(any(Transacao.class));
        verify(contaService, times(1)).listarContaId(1L);
        verify(contaService, times(1)).listarContaId(2L);
        verify(contaService, times(2)).criarConta(any(Conta.class));
        verifyNoMoreInteractions(transacaoService, contaService, falhaLogTransacaoService);
    }


}
