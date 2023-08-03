package gabriel.api.banco.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoConta {

    @Id
    @GeneratedValue
    private Long tipoContaID;

    private String tipoContaDescricao;

}
