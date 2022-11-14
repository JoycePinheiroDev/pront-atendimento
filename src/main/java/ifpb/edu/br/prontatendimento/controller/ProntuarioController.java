package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.Enfermeiro;
import ifpb.edu.br.prontatendimento.model.Paciente;
import ifpb.edu.br.prontatendimento.model.Prontuario;
import ifpb.edu.br.prontatendimento.repository.EnfermeiroRepository;
import ifpb.edu.br.prontatendimento.repository.PacienteRepository;
import ifpb.edu.br.prontatendimento.repository.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EnfermeiroRepository enfermeiroRepository;

    @GetMapping
    public List<Prontuario> get(){
        return prontuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> getById(@PathVariable(value = "id") Integer id){
        Optional<Prontuario> prontuario = prontuarioRepository.findById(id);
        if (prontuario.isPresent()){
            return new ResponseEntity<Prontuario>(prontuario.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Prontuario> post(@RequestBody Prontuario prontuario){

        Optional<Paciente> pacienteOptional = pacienteRepository.findById(prontuario.getPaciente().getId());
        Optional<Enfermeiro> enfermeiroOptional = enfermeiroRepository.findById(prontuario.getEnfermeiro().getId());

        if (pacienteOptional.isPresent() && enfermeiroOptional.isPresent()){
            Paciente paciente = pacienteOptional.get();
            Enfermeiro enfermeiro = enfermeiroOptional.get();

            prontuario.setPaciente(paciente);
            prontuario.setEnfermeiro(enfermeiro);

            prontuario.setSintomas(prontuario.getSintomas());
            prontuario.setClassificacao_risco(prontuario.getClassificacao_risco());
            prontuario.setPressao_arterial(prontuario.getPressao_arterial());
            prontuario.setTemperatura(prontuario.getTemperatura());
            prontuario.setAlergias(prontuario.getAlergias());

            /**
             * TODO:
             * VERIFICAR SE É POSSÍVEL PEGAR A DATA LOCAL NO FRONTEND*/
            prontuario.setData_atendimento(LocalDateTime.now());

            return new ResponseEntity<Prontuario>(prontuarioRepository.save(prontuario), HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> put(@PathVariable(value = "id") Integer id, @RequestBody Prontuario newProntuario){
        Optional<Prontuario> oldProntuario = prontuarioRepository.findById(id);
        if (oldProntuario.isPresent()){
            Prontuario prontuario = oldProntuario.get();
            prontuario.setSintomas(newProntuario.getSintomas());
            prontuario.setClassificacao_risco(newProntuario.getClassificacao_risco());
            prontuario.setPressao_arterial(newProntuario.getPressao_arterial());
            prontuario.setTemperatura(newProntuario.getTemperatura());
            prontuario.setAlergias(newProntuario.getAlergias());

            return new ResponseEntity<Prontuario>(prontuarioRepository.save(prontuario), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<Prontuario> prontuario = prontuarioRepository.findById(id);

        if (prontuario.isPresent()){
            prontuarioRepository.delete(prontuario.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
