package gabriel.api.banco.entities;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FalhaLogTransacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long falhaLogTransacaoId;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "transacaoID")
    private Transacao transacao;

    private String tipoErro;
    private Date timeStamp;

}
