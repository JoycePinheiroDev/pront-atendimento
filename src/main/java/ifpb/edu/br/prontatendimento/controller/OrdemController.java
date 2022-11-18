package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Ordem;
import ifpb.edu.br.prontatendimento.model.Paciente;
import ifpb.edu.br.prontatendimento.repository.OrdemRepository;
import ifpb.edu.br.prontatendimento.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ordem")
public class OrdemController {

    @Autowired
    private OrdemRepository ordemRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @GetMapping
    public List<Ordem> get(){
        return ordemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordem> getById(@PathVariable(value = "id") Integer id){
        Optional<Ordem> ordem = ordemRepository.findById(id);
        if (ordem.isPresent()){
            return new ResponseEntity<Ordem>(ordem.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Ordem> post(@RequestBody Ordem ordem){
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(ordem.getPaciente().getId());
        if (pacienteOptional.isPresent()){
            return new ResponseEntity<Ordem>(ordemRepository.save(ordem), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ordem> put(@PathVariable(value = "id") Integer id, @RequestBody Ordem newOrdem){
        Optional<Ordem> oldOrdem = ordemRepository.findById(id);
        if (oldOrdem.isPresent()){
            Ordem ordem = oldOrdem.get();
            /**
             * TODO: continuar a função*/
            return null;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<Ordem> ordem = ordemRepository.findById(id);
        if (ordem.isPresent()){
            ordemRepository.delete(ordem.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
