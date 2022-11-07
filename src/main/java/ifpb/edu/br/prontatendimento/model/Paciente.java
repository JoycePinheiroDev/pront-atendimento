package ifpb.edu.br.prontatendimento.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_paciente")
public class Paciente extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numero_sus;

    private String nome_mae;

    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

}
