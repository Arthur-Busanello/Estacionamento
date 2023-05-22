package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller;


import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Configuracao;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Marca;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.ConfiguracaoRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service.ConfiguracaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
@Controller
@Service
@RequestMapping(value = "/api/config")
public class ConfiguracaoController {
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;
    @Autowired
    private ConfiguracaoService configuracaoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id")final long id){
        return configuracaoService.findById(id);
//        final Configuracao configuracao = this.configuracaoRepository.findById(id).orElse(null);
//        return  configuracao == null
//                ? ResponseEntity.badRequest().body("valor nao encontrado.")
//                : ResponseEntity.ok(configuracao);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return configuracaoService.findAll();
//        return ResponseEntity.ok(this.configuracaoRepository.findAll());

    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody  final Configuracao configuracao){
        return configuracaoService.save(configuracao);
//        this.configuracaoRepository.save(configuracao);
//        return ResponseEntity.ok("cadastrado com sucesso.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody final Configuracao configuracao
    ) {
        return configuracaoService.update(id,configuracao);
//        if (id.equals(configuracao.getId())) {
//            this.configuracaoRepository.save(configuracao);
//        } else {
//            return ResponseEntity.badRequest().body("ID não encontrado !");
//        }
//        return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
    }


    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestParam("id") final Long id
    ){
        return configuracaoService.delete(id);
//        final Configuracao configuracao1 = this.configuracaoRepository.findById(id).orElse(null);
//
//        this.configuracaoRepository.delete(configuracao1);
//        return ResponseEntity.ok("Registro Excluido");
    }
}
