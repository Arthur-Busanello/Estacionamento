package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller;


import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Modelo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.ModeloRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller.exception.DuplicateKeyException;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.ModeloDTOS;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ModeloDTOS modeloDTOS) {
        return modeloService.create(modeloDTOS);
//        try {
//            this.modeloRepository.save(modelo);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar marca.");
//        }

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException (MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldname = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldname, errorMessage);
        });

        return errors;
    };

    @ExceptionHandler(br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller.exception.DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody @Valid ModeloDTOS modeloDTOS
    ) {
        return modeloService.update(id, modeloDTOS);
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

