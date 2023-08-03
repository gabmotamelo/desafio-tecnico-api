package gabriel.api.banco.repositories;

import gabriel.api.banco.entities.FalhaLogTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FalhaLogTransacaoRepository extends JpaRepository<FalhaLogTransacao, Long> {
}
