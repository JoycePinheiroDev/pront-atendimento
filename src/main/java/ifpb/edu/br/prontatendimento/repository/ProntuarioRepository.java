package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {
}
