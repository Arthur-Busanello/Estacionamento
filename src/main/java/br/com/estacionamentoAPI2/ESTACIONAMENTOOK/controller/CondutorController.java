package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Condutor;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.CondutorRepository;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.CondutorDTOS;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service.CondutorService;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Controller
@Service
@RequestMapping(value = "/api/condutor")
public class CondutorController {
    @Autowired
    private CondutorRepository condutorRepository;
    @Autowired
    private CondutorService condutorService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id")final long id){
        return condutorService.findById(id);
    }
//        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
//        return  condutor == null
//                ? ResponseEntity.badRequest().body("valor nao encontrado.")
//                : ResponseEntity.ok(condutor);
//    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return condutorService.findAll();
//        return ResponseEntity.ok(this.condutorRepository.findAll());
    }
    @GetMapping("/ativo")
    public ResponseEntity<?> findAtivos() {
        return condutorService.findByAtivo(true);
//        try {
//            List<Condutor> condutores = condutorRepository.findByAtivoTrue();
//            return new ResponseEntity<>(condutores, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody  @Valid CondutorDTOS condutorDTOS){
        Condutor condutor = new Condutor();
        BeanUtils.copyProperties(condutorDTOS,condutor);

        return condutorService.create(condutor);
    }
//        this.condutorRepository.save(condutor);
//        return ResponseEntity.ok("cadastrado com sucesso.");

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody @Valid CondutorDTOS condutorDTOS
    ) {
        return condutorService.update(id,condutorDTOS);
//        if (id.equals(condutor.getId())) {
//            this.condutorRepository.save(condutor);
//        } else {
//            return ResponseEntity.badRequest().body("ID não encontrado !");
//        }
//        return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        return condutorService.delete(id);
//        final Condutor condutor1 = this.condutorRepository.findById(id).orElse(null);
//
//        this.condutorRepository.delete(condutor1);
//        return ResponseEntity.ok("Registro Excluido");
    }
}
