package ifpb.edu.br.prontatendimento.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_medico")
public class Medico extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String CRM;

    private String especialidade;

    @OneToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

}
