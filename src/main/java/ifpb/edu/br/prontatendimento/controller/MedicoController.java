package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Medico;
import ifpb.edu.br.prontatendimento.model.Pessoa;
import ifpb.edu.br.prontatendimento.repository.MedicoRepository;
import ifpb.edu.br.prontatendimento.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Medico> get(){
        return medicoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> getById(@PathVariable(value = "id") Integer id){
        Optional<Medico> medico = medicoRepository.findById(id);
        if (medico.isPresent()){
            return new ResponseEntity<Medico>(medico.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<Medico> post(@RequestBody Medico medico){
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(medico.getPessoa().getId());
        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            medico.setPessoa(pessoa);
            medicoRepository.save(medico);
            return new ResponseEntity<Medico>(medico, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> put(@PathVariable(value = "id") Integer id, @RequestBody Medico newMedico) {
        Optional<Medico> oldMedico = medicoRepository.findById(id);
        if (oldMedico.isPresent()) {
            Medico medico = oldMedico.get();
            medico.setCRM(newMedico.getCRM());
            medico.setEspecialidade(newMedico.getEspecialidade());
            medicoRepository.save(medico);

            return new ResponseEntity<Medico>(medico, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")  Integer  id){
        Optional<Medico> medico = medicoRepository.findById(id);
        if (medico.isPresent()){
            medicoRepository.delete(medico.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
