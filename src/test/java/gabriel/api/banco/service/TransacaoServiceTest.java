package gabriel.api.banco.services;

import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.entities.TipoTransacao;
import gabriel.api.banco.entities.Transacao;
import gabriel.api.banco.repositories.TransacaoRepository;
import gabriel.api.banco.utils.TransacaoUtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarTransacoes() {
        List<Transacao> transacoesSimuladas = new ArrayList<>();
        transacoesSimuladas.add(TransacaoUtilsTest.criarTransacaoExemplo());

        when(transacaoRepository.findAll()).thenReturn(transacoesSimuladas);

        List<Transacao> result = transacaoService.listarTransacoes();

        assertEquals(transacoesSimuladas, result);
        verify(transacaoRepository, times(1)).findAll();
    }

    @Test
    public void testCriarTransacao() {
        Transacao transacaoExemplo = TransacaoUtilsTest.criarTransacaoExemplo();

        when(transacaoRepository.save(any(Transacao.class))).thenReturn(transacaoExemplo);

        Transacao result = transacaoService.criarTransacao(transacaoExemplo);

        assertEquals(transacaoExemplo, result);
        verify(transacaoRepository, times(1)).save(any(Transacao.class));
    }

    @Test
    public void testListarTransacaoId() {
        Transacao transacaoExemplo = TransacaoUtilsTest.criarTransacaoExemplo();

        when(transacaoRepository.findById(1L)).thenReturn(Optional.of(transacaoExemplo));

        Optional<Transacao> result = transacaoService.listarTransacaoId(1L);

        assertTrue(result.isPresent());
        assertEquals(transacaoExemplo, result.get());
        verify(transacaoRepository, times(1)).findById(1L);
    }

    @Test
    public void testListarTransacaoId_NotFound() {
        when(transacaoRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Transacao> result = transacaoService.listarTransacaoId(2L);

        assertFalse(result.isPresent());
        verify(transacaoRepository, times(1)).findById(2L);
    }

}
