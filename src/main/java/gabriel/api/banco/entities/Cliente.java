package gabriel.api.banco.entities;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue
    private int clienteID;

    private String nome;
    private String endereco;
    private String cep;
    private String telefone;
    private String email;
    private String cidade;
    private String estado;
    private String cpf;
    private String rg;

    @OneToMany(mappedBy = "cliente")
    private List<ClienteConta> contas;

}
