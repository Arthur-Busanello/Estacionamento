package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.controller;



import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Movimentacao;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.MovimentacaoRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
@Controller
@Service
@RequestMapping(value = "api/movimentacao")
public class MovimentacaoController {
    @Autowired
        private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private MovimentacaoService movimentacaoService;


    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id")final long id){
        return movimentacaoService.findById(id);
//        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
//        return  movimentacao == null
//                ? ResponseEntity.badRequest().body("valor nao encontrado.")
//                : ResponseEntity.ok(movimentacao);
    }
    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta(){
        return movimentacaoService.findAll();
//        return ResponseEntity.ok(this.movimentacaoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody  final Movimentacao movimentacao){
        return movimentacaoService.save(movimentacao);
//        this.movimentacaoRepository.save(movimentacao);
//        return ResponseEntity.ok("cadastrado com sucesso.");
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable final Long id, @RequestBody final Movimentacao movimentacao
    ) {
        return movimentacaoService.update(id,movimentacao);
//        if (id.equals(movimentacao.getId())) {
//            this.movimentacaoRepository.save(movimentacao);
//        } else {
//            return ResponseEntity.badRequest().body("ID não encontrado !");
//        }
//        return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
    }


    @DeleteMapping
    public ResponseEntity<?> delete(
            @RequestParam("id") final Long id
    ){
        return movimentacaoService.delete(id);
//        final Movimentacao movimentacao1 = this.movimentacaoRepository.findById(id).orElse(null);
//
//        this.movimentacaoRepository.delete(movimentacao1);
//        return ResponseEntity.ok("Registro Excluido");
    }
}

