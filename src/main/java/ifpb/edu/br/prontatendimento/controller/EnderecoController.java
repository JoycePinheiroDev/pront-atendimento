package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Endereco;
import ifpb.edu.br.prontatendimento.model.Medico;
import ifpb.edu.br.prontatendimento.model.Pessoa;
import ifpb.edu.br.prontatendimento.repository.EnderecoRepository;
import ifpb.edu.br.prontatendimento.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    private List<Endereco> get(){
        return enderecoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getById(@PathVariable(value = "id") Integer id){
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()){
            return new ResponseEntity<Endereco>(endereco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Endereco> post(@RequestBody Endereco endereco){
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(endereco.getPessoa().getId());
        if (pessoaOptional.isPresent()){
            Pessoa pessoa = pessoaOptional.get();
            endereco.setPessoa(pessoa);
            enderecoRepository.save(endereco);

            return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> put(@PathVariable(value = "id") Integer id, @RequestBody Endereco newEndereco){
        Optional<Endereco> oldEndereco = enderecoRepository.findById(id);
        if (oldEndereco.isPresent()){
            Endereco endereco = oldEndereco.get();
            endereco.setRua(newEndereco.getRua());
            endereco.setNumero(newEndereco.getNumero());
            endereco.setBairro(newEndereco.getBairro());
            endereco.setCidade(newEndereco.getCidade());
            endereco.setEstado(newEndereco.getEstado());
            enderecoRepository.save(endereco);

            return new ResponseEntity<Endereco>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if (endereco.isPresent()){
            enderecoRepository.delete(endereco.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
