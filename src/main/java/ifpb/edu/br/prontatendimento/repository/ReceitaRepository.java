package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
}
