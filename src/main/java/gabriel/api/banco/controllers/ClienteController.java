package gabriel.api.banco.controllers;


import gabriel.api.banco.entities.Cliente;
import gabriel.api.banco.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente")
    @Operation(summary = "Retorne os clientes.")
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }
    @GetMapping("/cliente/{clientId}")
    @Operation(summary = "Retorne todas as transações pelo ID da Conta")
    public Optional<Cliente> listarClientes(@PathVariable("clientId") String id){
        return clienteService.listarClienteId(id);
    }
    @PostMapping("/cliente")
    @Operation(summary = "Criação do Cliente.")
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }
    @PutMapping("/cliente/{clienteId}")
    @Operation(summary = "Atualizar dados do Cliente.")
    public Cliente atualizarCliente(@PathVariable("clienteId") int id, @RequestBody Cliente cliente) {
        cliente.setClienteID(id);
        return clienteService.salvar(cliente);
    }
}





