package ifpb.edu.br.prontatendimento.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_prontuario")
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_enfermeiro", nullable = false)
    private Enfermeiro enfermeiro;

    private ClassificacaoRisco classificacao_risco;

    private String sintomas;

    private String pressao_arterial;

    private Double temperatura;

    private String alergias;

    private LocalDateTime data_atendimento;

}
