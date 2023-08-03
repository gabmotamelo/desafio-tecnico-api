package gabriel.api.banco.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteConta {

    @Id
    @GeneratedValue
    private Long clienteContaID;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "clienteID")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "contaID")
    private Conta conta;

}
