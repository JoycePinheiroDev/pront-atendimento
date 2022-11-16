package ifpb.edu.br.prontatendimento.controller;

import ifpb.edu.br.prontatendimento.model.*;
import ifpb.edu.br.prontatendimento.repository.MedicoRepository;
import ifpb.edu.br.prontatendimento.repository.PacienteRepository;
import ifpb.edu.br.prontatendimento.repository.SolicitacaoExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exames")
public class SolicitacaoExameController {

    @Autowired
    private SolicitacaoExameRepository solicitacaoExameRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public List<SolicitacaoExame> get(){
        return solicitacaoExameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoExame> getById(@PathVariable(value = "id") Integer id){
        Optional<SolicitacaoExame> solicitacaoExame = solicitacaoExameRepository.findById(id);
        if (solicitacaoExame.isPresent()){
            return new ResponseEntity<SolicitacaoExame>(solicitacaoExame.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<SolicitacaoExame> post(@RequestBody SolicitacaoExame solicitacaoExame){

        Optional<Paciente> pacienteOptional = pacienteRepository.findById(solicitacaoExame.getPaciente().getId());
        Optional<Medico> medicoOptional = medicoRepository.findById(solicitacaoExame.getMedico().getId());

        if (pacienteOptional.isPresent() && medicoOptional.isPresent()){
            Paciente paciente = pacienteOptional.get();
            Medico medico = medicoOptional.get();

            solicitacaoExame.setPaciente(paciente);
            solicitacaoExame.setMedico(medico);

            solicitacaoExame.setExames(solicitacaoExame.getExames());
            /**
             * TODO:
             * VERIFICAR SE É POSSÍVEL PEGAR A DATA LOCAL NO FRONTEND*/
            solicitacaoExame.setData(LocalDateTime.now());

            return new ResponseEntity<SolicitacaoExame>(solicitacaoExameRepository.save(solicitacaoExame), HttpStatus.CREATED);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoExame> put(@PathVariable(value = "id") Integer id, @RequestBody SolicitacaoExame newSolicitacaoExame){
        Optional<SolicitacaoExame> oldSolicitacaoExame = solicitacaoExameRepository.findById(id);
        if (oldSolicitacaoExame.isPresent()){
            SolicitacaoExame solicitacaoExame = oldSolicitacaoExame.get();
            solicitacaoExame.setExames(newSolicitacaoExame.getExames());

            return new ResponseEntity<SolicitacaoExame>(solicitacaoExameRepository.save(solicitacaoExame), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Integer id){
        Optional<SolicitacaoExame> solicitacaoExame = solicitacaoExameRepository.findById(id);

        if (solicitacaoExame.isPresent()){
            solicitacaoExameRepository.delete(solicitacaoExame.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
