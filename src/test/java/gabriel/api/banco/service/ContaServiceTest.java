package gabriel.api.banco.services;

import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.repositories.ContaRepository;
import gabriel.api.banco.utils.ContaUtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarContas() {
        List<Conta> contasSimuladas = new ArrayList<>();
        contasSimuladas.add(ContaUtilsTest.criarContaExemplo());

        when(contaRepository.findAll()).thenReturn(contasSimuladas);

        List<Conta> result = contaService.listarContas();

        assertEquals(contasSimuladas, result);
        verify(contaRepository, times(1)).findAll();
    }

    @Test
    public void testCriarConta() {
        Conta contaExemplo = ContaUtilsTest.criarContaExemplo();

        when(contaRepository.save(any(Conta.class))).thenReturn(contaExemplo);

        Conta result = contaService.criarConta(contaExemplo);

        assertEquals(contaExemplo, result);
        verify(contaRepository, times(1)).save(any(Conta.class));
    }

    @Test
    public void testListarContaId() {
        Conta contaExemplo = ContaUtilsTest.criarContaExemplo();

        when(contaRepository.findById(1L)).thenReturn(Optional.of(contaExemplo));

        Optional<Conta> result = contaService.listarContaId(1L);

        assertTrue(result.isPresent());
        assertEquals(contaExemplo, result.get());
        verify(contaRepository, times(1)).findById(1L);
    }

    @Test
    public void testListarContaId_NotFound() {
        when(contaRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Conta> result = contaService.listarContaId(2L);

        assertFalse(result.isPresent());
        verify(contaRepository, times(1)).findById(2L);
    }

}
