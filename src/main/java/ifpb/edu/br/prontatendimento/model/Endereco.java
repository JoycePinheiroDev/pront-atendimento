package ifpb.edu.br.prontatendimento.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

}
