package gabriel.api.banco.services;

import gabriel.api.banco.entities.FalhaLogTransacao;
import gabriel.api.banco.repositories.FalhaLogTransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FalhaLogTransacaoService {

    @Autowired
    private FalhaLogTransacaoRepository falhaLogTransacaoRepository;

    public FalhaLogTransacao salvarFalhaLogTransacao(FalhaLogTransacao falhaLogTransacao) {
        return falhaLogTransacaoRepository.save(falhaLogTransacao);
    }
}
