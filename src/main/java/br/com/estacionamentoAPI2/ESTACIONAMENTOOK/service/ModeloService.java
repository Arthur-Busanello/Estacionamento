package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;


import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Modelo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;

    @Autowired
    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Modelo modelo) {

    }
    public ResponseEntity<?> findById(Long id) {
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        return modelo == null
                ? ResponseEntity.badRequest().body("valor nao encontrado.")
                : ResponseEntity.ok(modelo);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.modeloRepository.findAll());
    }

    public ResponseEntity<String> save(Modelo modelo) {
        try {
            this.modeloRepository.save(modelo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
        }
    }

    public ResponseEntity<?> update(Long id, Modelo modelo) {
        if (id.equals(modelo.getId())) {
            this.modeloRepository.save(modelo);
            return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
    }

    public ResponseEntity<?> delete(Long id) {
        final Modelo modelo = this.modeloRepository.findById(id).orElse(null);
        if (modelo == null) {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
        this.modeloRepository.delete(modelo);
        return ResponseEntity.ok("Registro Excluido");
    }



}




