package gabriel.api.banco.entities;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @GeneratedValue
    private int contaID;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "tipoContaID")
    private TipoConta tipoConta;

    @ManyToOne
    @JoinColumn(name = "statusContaID")
    private TipoStatusConta statusConta;

    private BigDecimal saldoAtual;

    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    @OneToMany(mappedBy = "conta")
    private List<Transacao> transacoes;

}
