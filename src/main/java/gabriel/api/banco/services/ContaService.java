package gabriel.api.banco.services;


import gabriel.api.banco.entities.Conta;
import gabriel.api.banco.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }
    public Conta criarConta(Conta conta){
        return contaRepository.save(conta);
    }
    public Optional<Conta> listarContaId(Long id){
        return contaRepository.findById(id);
    }

}
