package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Enfermeiro;
import ifpb.edu.br.prontatendimento.model.Pessoa;
import ifpb.edu.br.prontatendimento.repository.EnfermeiroRepository;
import ifpb.edu.br.prontatendimento.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enfermeiros")
public class EnfermeiroController {

    @Autowired
    private EnfermeiroRepository enfermeiroRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Enfermeiro> get(){
        return enfermeiroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enfermeiro> getById(@PathVariable(value = "id") Integer id){
        Optional<Enfermeiro> enfermeiro = enfermeiroRepository.findById(id);
        if (enfermeiro.isPresent()){
            return new ResponseEntity<Enfermeiro>(enfermeiro.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Enfermeiro> post(@RequestBody Enfermeiro enfermeiro) {
        return new ResponseEntity<Enfermeiro>(enfermeiroRepository.save(enfermeiro), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enfermeiro> put(@PathVariable(value = "id") Integer id, @RequestBody Enfermeiro newEnfermeiro){
        Optional<Enfermeiro> oldEnfermeiro = enfermeiroRepository.findById(id);
        if (oldEnfermeiro.isPresent()){
            Enfermeiro enfermeiro = oldEnfermeiro.get();
            enfermeiro.setCOREN(newEnfermeiro.getCOREN());
            enfermeiroRepository.save(enfermeiro);

            return new ResponseEntity<Enfermeiro>(enfermeiro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")  Integer  id){
        Optional<Enfermeiro> enfermeiro = enfermeiroRepository.findById(id);
        if (enfermeiro.isPresent()){
            enfermeiroRepository.delete(enfermeiro.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
