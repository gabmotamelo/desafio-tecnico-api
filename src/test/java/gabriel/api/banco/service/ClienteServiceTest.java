package gabriel.api.banco.service;

import gabriel.api.banco.entities.Cliente;
import gabriel.api.banco.repositories.ClienteRepository;
import gabriel.api.banco.services.ClienteService;
import gabriel.api.banco.utils.ClienteUtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(ClienteUtilsTest.criarClienteExemplo());
        clientes.add(ClienteUtilsTest.criarClienteExemplo());

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.listarClientes();

        assertEquals(2, result.size());
        assertEquals("Exemplo", result.get(0).getNome());
        assertEquals("Exemplo", result.get(1).getNome());

        verify(clienteRepository, times(1)).findAll();
        verifyNoMoreInteractions(clienteRepository);
    }

    @Test
    public void testSalvarCliente() {
        Cliente cliente = ClienteUtilsTest.criarClienteExemplo();

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente savedCliente = clienteService.salvar(cliente);

        assertNotNull(savedCliente);
        assertEquals("Exemplo", savedCliente.getNome());

        verify(clienteRepository, times(1)).save(cliente);
        verifyNoMoreInteractions(clienteRepository);
    }

    @Test
    public void testListarClientePorId() {
        Cliente cliente = ClienteUtilsTest.criarClienteExemplo();

        when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.listarClienteId("1");

        assertTrue(result.isPresent());
        assertEquals("Exemplo", result.get().getNome());

        verify(clienteRepository, times(1)).findById("1");
        verifyNoMoreInteractions(clienteRepository);
    }

}
