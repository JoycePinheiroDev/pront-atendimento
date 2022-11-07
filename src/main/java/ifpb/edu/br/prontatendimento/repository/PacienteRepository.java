package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{
}
