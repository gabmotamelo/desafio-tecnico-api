package gabriel.api.banco.entities;

import lombok.*;
import jakarta.persistence.*;



@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoTransacao {

    @Id
    @GeneratedValue
    private Long tipoTransacaoID;

    private String descricao;

}
