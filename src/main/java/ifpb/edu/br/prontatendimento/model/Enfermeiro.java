package ifpb.edu.br.prontatendimento.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@PrimaryKeyJoinColumn(name="id_pessoa")
@Table(name = "tb_enfermeiro")
public class Enfermeiro extends Pessoa{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String COREN;

}
