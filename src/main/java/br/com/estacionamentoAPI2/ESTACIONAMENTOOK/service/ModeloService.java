package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;


import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Marca;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Modelo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.MarcaRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.ModeloRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.ModeloDTOS;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ModeloService {

    private final ModeloRepository modeloRepository;


    @Autowired
    public ModeloService(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }
    @Autowired
    private MarcaRepository marcaRepository;

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

    @Transactional
    public ResponseEntity<?> create(ModeloDTOS modeloDTOS) {
        try {

            Modelo modelo = new Modelo();
            modelo.setNome(modeloDTOS.getNome());
            Long marca_id = modeloDTOS.getMarca();
            Marca marca1 = marcaRepository.getById(marca_id);
            modelo.setMarca(marca1);
            modelo.setAtualizacao(LocalDateTime.now());
            modelo.setAtivo(true);
            modeloRepository.save(modelo);
            TransactionAspectSupport.currentTransactionStatus().flush();
            return ResponseEntity.status(HttpStatus.CREATED).body(modelo);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());

        }
    }
@Transactional
    public ResponseEntity<?> update(Long id, ModeloDTOS modeloDTOS) {
        Optional<Modelo> optionalModelo = modeloRepository.findById(id);
        if (optionalModelo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modelo not found with ID: " + id);
        } else {
            Modelo modelo1 = optionalModelo.get();
            BeanUtils.copyProperties(modeloDTOS, modelo1);
            modelo1.setAtualizacao(LocalDateTime.now());
            modeloRepository.save(modelo1);
            return ResponseEntity.ok().body("Modelo atualizado com sucesso");
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




