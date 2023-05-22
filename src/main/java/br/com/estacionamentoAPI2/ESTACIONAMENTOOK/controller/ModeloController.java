package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller;


import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Modelo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.ModeloRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Service
@RequestMapping(value = "/api/modelo")
public class ModeloController {
    @Autowired
    private ModeloRepository modeloRepository;
    @Autowired
    private ModeloService modeloService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id")final long id){
        return modeloService.findById(id);
//        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
//        return  modelo == null
//                ? ResponseEntity.badRequest().body("valor nao encontrado.")
//                : ResponseEntity.ok(modelo);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return modeloService.findAll();
//        return ResponseEntity.ok().body(this.modeloRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Modelo>> findAtivos() {
        try {
            List<Modelo> condutores = modeloRepository.findByAtivoTrue();
            return new ResponseEntity<>(condutores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody final Modelo modelo) {
        return modeloService.save(modelo);
//        try {
//            this.modeloRepository.save(modelo);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar marca.");
//        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody final Modelo modelo
    ) {
        return modeloService.update(id, modelo);
//        if (id.equals(modelo.getId())) {
//            this.modeloRepository.save(modelo);
//        } else {
//            return ResponseEntity.badRequest().body("ID não encontrado !");
//        }
//        return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
    }


    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestParam("id") final Long id
    ){
        return modeloService.delete(id);
    }
//        final Modelo modelo1 = this.modeloRepository.findById(id).orElse(null);
//
//        this.modeloRepository.delete(modelo1);
//        return ResponseEntity.ok("Registro Excluido");
//    }
}

