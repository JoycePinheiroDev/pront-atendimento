package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Medico;
import ifpb.edu.br.prontatendimento.model.Paciente;
import ifpb.edu.br.prontatendimento.model.Receita;
import ifpb.edu.br.prontatendimento.repository.MedicoRepository;
import ifpb.edu.br.prontatendimento.repository.PacienteRepository;
import ifpb.edu.br.prontatendimento.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public List<Receita> get(){
        return receitaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getById(@PathVariable(value = "id") Integer id){
        Optional<Receita> receita = receitaRepository.findById(id);
        if (receita.isPresent()){
            return new ResponseEntity<Receita>(receita.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Receita> post(@RequestBody Receita receita){
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(receita.getPaciente().getId());
        Optional<Medico> medicoOptional = medicoRepository.findById(receita.getMedico().getId());

        if (pacienteOptional.isPresent() && medicoOptional.isPresent()){
            Paciente paciente = pacienteOptional.get();
            Medico medico = medicoOptional.get();

            receita.setPaciente(paciente);
            receita.setMedico(medico);

            receita.setRemedio(receita.getRemedio());
            receita.setPosologia(receita.getPosologia());
            /**
             * TODO:
             * VERIFICAR SE TEM COMO PEGAR A DATA E HORA DO FRONTEND*/
            receita.setData_atendimento(LocalDateTime.now());

            return new ResponseEntity<Receita>(receitaRepository.save(receita), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> put(@PathVariable(value = "id") Integer id, @RequestBody Receita newReceita){
        Optional<Receita> oldReceita = receitaRepository.findById(id);
        if (oldReceita.isPresent()){
            Receita receita = oldReceita.get();
            receita.setRemedio(newReceita.getRemedio());
            receita.setPosologia(newReceita.getPosologia());

            return new ResponseEntity<Receita>(receitaRepository.save(receita), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<Receita> receita = receitaRepository.findById(id);
        if (receita.isPresent()){
            receitaRepository.delete(receita.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
