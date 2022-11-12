package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Recepcionista;
import ifpb.edu.br.prontatendimento.repository.PessoaRepository;
import ifpb.edu.br.prontatendimento.repository.RecepcionistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recepcionistas")
public class RecepcionistaController {

    @Autowired
    private RecepcionistaRepository recepcionistaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Recepcionista> get(){
        return recepcionistaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recepcionista> getById(@PathVariable(value = "id") Integer id){
        Optional<Recepcionista> recepcionista = recepcionistaRepository.findById(id);
        if (recepcionista.isPresent()){
            return new ResponseEntity<Recepcionista>(recepcionista.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Recepcionista> post(@RequestBody Recepcionista recepcionista){
        return new ResponseEntity<Recepcionista>(recepcionistaRepository.save(recepcionista), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recepcionista> put(@PathVariable(value = "id") Integer id, @RequestBody Recepcionista newRecepcionista){
        Optional<Recepcionista> oldRecepcionista = recepcionistaRepository.findById(id);
        if(oldRecepcionista.isPresent()){
            Recepcionista recepcionista = oldRecepcionista.get();
            recepcionistaRepository.save(recepcionista);

            return new ResponseEntity<Recepcionista>(recepcionista, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<Recepcionista> recepcionista = recepcionistaRepository.findById(id);
        if (recepcionista.isPresent()){
            recepcionistaRepository.delete(recepcionista.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

}
