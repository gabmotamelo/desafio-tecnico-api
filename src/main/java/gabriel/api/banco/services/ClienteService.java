package gabriel.api.banco.services;


import gabriel.api.banco.entities.Cliente;
import gabriel.api.banco.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Optional<Cliente> listarClienteId(String id){
        return clienteRepository.findById(id);
    }

}
