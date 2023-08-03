package gabriel.api.banco.entities;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TipoStatusConta {

    @Id
    @GeneratedValue
    private Long statusContaID;

    private String statusContaDescricao;

}
