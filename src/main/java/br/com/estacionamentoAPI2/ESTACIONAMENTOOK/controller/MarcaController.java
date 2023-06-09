package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Marca;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.MarcaRepository;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.MarcaDTOS;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@Service
@RequestMapping(value = "/api/marca")
public class MarcaController {
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private final MarcaService marcaService;
    @Autowired
    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id")final long id){
        return marcaService.findById(id);
//        final Marca marca = this.marcaRepository.findById(id).orElse(null);
//        return  marca == null
//                ? ResponseEntity.badRequest().body("valor nao encontrado.")
//                : ResponseEntity.ok(marca);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return marcaService.findAll();
//        return ResponseEntity.ok(this.marcaRepository.findAll());
    }

    @GetMapping("/ativo")
    public ResponseEntity<List<Marca>> findAtivos() {
        try {
            List<Marca> condutores = marcaRepository.findByAtivoTrue();
            return new ResponseEntity<>(condutores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
@PostMapping("/create")
public ResponseEntity<?> create(@RequestBody @Valid MarcaDTOS marcaDTOS) {
        return marcaService.create(marcaDTOS);
//    try {
//        this.marcaRepository.save(marca);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar marca.");
//    }
}

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody @Valid MarcaDTOS marcaDTOS
    ) {
        return marcaService.update(id, marcaDTOS);
//        if (id.equals(marca.getId())) {
//            this.marcaRepository.save(marca);
//        } else {
//            return ResponseEntity.badRequest().body("ID não encontrado !");
//        }
//        return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable  Long id) {
        return  marcaService.delete(id);
    }
//    @DeleteMapping
//    public ResponseEntity<?> delete(
//            @RequestParam("id") final Long id){
//        return marcaService.delete(id);
////        final Marca marca1 = this.marcaRepository.findById(id).orElse(null);
////
////        this.marcaRepository.delete(marca1);
////        return ResponseEntity.ok("Registro Excluido");
//    }
}


