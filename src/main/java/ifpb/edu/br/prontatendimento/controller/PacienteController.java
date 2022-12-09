package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Paciente;
import ifpb.edu.br.prontatendimento.model.Pessoa;
import ifpb.edu.br.prontatendimento.repository.PacienteRepository;
import ifpb.edu.br.prontatendimento.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Paciente> get(){
        return pacienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable(value = "id") Integer id){
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()){
            return new ResponseEntity<Paciente>(paciente.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Paciente> post(@RequestBody Paciente paciente){
            return new ResponseEntity<Paciente>(pacienteRepository.save(paciente), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> put(@PathVariable(value = "id") Integer id, @RequestBody Paciente newPaciente){
        Optional<Paciente> oldPaciente = pacienteRepository.findById(id);
        if(oldPaciente.isPresent()){
            Paciente paciente = oldPaciente.get();
            paciente.setNumero_sus(newPaciente.getNumero_sus());
            paciente.setResponsavel(newPaciente.getResponsavel());
            pacienteRepository.save(paciente);

            return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()){
            pacienteRepository.delete(paciente.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
