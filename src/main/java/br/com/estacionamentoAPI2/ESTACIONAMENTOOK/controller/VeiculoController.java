package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller;



import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Veiculo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.VeiculoRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.VeiculoDTOS;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@Controller
@Service
@RequestMapping(value = "api/veiculo")
public class VeiculoController {
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id")final long id){
        return veiculoService.findById(id);
//        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
//        return  veiculo == null
//                ? ResponseEntity.badRequest().body("valor nao encontrado.")
//                : ResponseEntity.ok(veiculo);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return veiculoService.findAll();
//        return ResponseEntity.ok(this.veiculoRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody  @Valid VeiculoDTOS veiculoDTOS){
        return veiculoService.create(veiculoDTOS);
//        this.veiculoRepository.save(veiculo);
//        return ResponseEntity.ok("cadastrado com sucesso.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable final Long id, @RequestBody final VeiculoDTOS veiculoDTOS
    ) {
        return veiculoService.update(id, veiculoDTOS);
//        if (id.equals(veiculo.getId())) {
//            this.veiculoRepository.save(veiculo);
//        } else {
//            return ResponseEntity.badRequest().body("ID não encontrado !");
//        }
//        return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        return veiculoService.delete(id);
//        final Veiculo veiculo1 = this.veiculoRepository.findById(id).orElse(null);
//
//        this.veiculoRepository.delete(veiculo1);
//        return ResponseEntity.ok("Registro Excluido");
    }
}