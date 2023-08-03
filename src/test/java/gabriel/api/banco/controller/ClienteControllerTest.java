package gabriel.api.banco.controller;

import gabriel.api.banco.controllers.ClienteController;
import gabriel.api.banco.entities.Cliente;
import gabriel.api.banco.services.ClienteService;
import gabriel.api.banco.utils.ClienteUtilsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    public void testListarClientes() throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(ClienteUtilsTest.criarClienteExemplo());
        clientes.add(ClienteUtilsTest.criarClienteExemplo());

        when(clienteService.listarClientes()).thenReturn(clientes);

        mockMvc.perform(get("/cliente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testListarClientePorId() throws Exception {
        Cliente cliente = ClienteUtilsTest.criarClienteExemplo();

        when(clienteService.listarClienteId("1")).thenReturn(Optional.of(cliente));

        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(cliente.getNome()));
    }

    @Test
    public void testCriarCliente() throws Exception {
        Cliente novoCliente = ClienteUtilsTest.criarClienteExemplo();

        mockMvc.perform(post("/cliente")
                        .contentType("application/json")
                        .content(ClienteUtilsTest.toJson(novoCliente)))
                .andExpect(status().isOk());

        verify(clienteService, times(1)).salvar(any());
    }

    @Test
    public void testAtualizarCliente() throws Exception {
        Cliente clienteExistente = ClienteUtilsTest.criarClienteExemplo();

        when(clienteService.salvar(any())).thenReturn(clienteExistente);

        mockMvc.perform(put("/cliente/1")
                        .contentType("application/json")
                        .content(ClienteUtilsTest.toJson(clienteExistente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(clienteExistente.getNome()));
    }

    // Teste outros métodos e cenários conforme necessário
}
