package ifpb.edu.br.prontatendimento.repository;

import ifpb.edu.br.prontatendimento.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
