package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;




import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Marca;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.MarcaRepository;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.MarcaDTOS;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MarcaService {
    private MarcaRepository marcaRepository;

    @Autowired
    public void MarcaRepository(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }



    public ResponseEntity<?> findById(Long id) {
        final Marca marca = this.marcaRepository.findById(id).orElse(null);
        return marca == null
                ? ResponseEntity.badRequest().body("valor nao encontrado.")
                : ResponseEntity.ok(marca);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.marcaRepository.findAll());
    }

    @Transactional
    public ResponseEntity<?> create(MarcaDTOS marcaDTOS) {
        Marca marca = new Marca();
        BeanUtils.copyProperties(marcaDTOS, marca);
        try {
            marca.setAtivo(true);
            marcaRepository.save(marca);
            TransactionAspectSupport.currentTransactionStatus().flush();
            return ResponseEntity.status(HttpStatus.CREATED).body(marca);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());
        }
//        try {
//            this.marcaRepository.save(marcaDTOS);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
//        }
    }
    @Transactional
    public ResponseEntity<?> update(Long id, MarcaDTOS marcaDTOS) {
        Optional<Marca> optionalMarca = marcaRepository.findById(id);
        if (optionalMarca.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Marca not found with ID: " + id);
        } else {
            Marca marca1 = optionalMarca.get();
            BeanUtils.copyProperties(marcaDTOS, marca1);
            marca1.setAtualizacao(LocalDateTime.now());
            marcaRepository.save(marca1);
            return ResponseEntity.ok().body("Marca atualizado com sucesso");
        }
    }

    public ResponseEntity<?> delete(Long id) {
        final Marca marca = this.marcaRepository.findById(id).orElse(null);
        if (marca == null) {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
        this.marcaRepository.delete(marca);
        return ResponseEntity.ok("Registro Excluido");
    }

//    public ResponseEntity<?> delete(Long id) {
//        final Marca marca = this.marcaRepository.findById(id).orElse(null);
//        if (marca == null) {
//            return ResponseEntity.badRequest().body("ID não encontrado !");
//        }
//        this.marcaRepository.delete(marca);
//        return ResponseEntity.ok("Registro Excluido");
//    }


}


