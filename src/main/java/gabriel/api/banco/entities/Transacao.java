package gabriel.api.banco.entities;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue
    private int transacaoID;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;

    private Date dataTransacao;
    private BigDecimal valorTransacao;
    private BigDecimal novoSaldo;

    @ManyToOne
    @JoinColumn(name = "contaID")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    public enum TipoTransacao {
        SAQUE,
        DEPOSITO,
        TRANSFERENCIA;
    }
}
