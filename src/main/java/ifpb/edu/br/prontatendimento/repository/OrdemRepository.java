package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Ordem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdemRepository extends JpaRepository<Ordem, Integer> {
}
