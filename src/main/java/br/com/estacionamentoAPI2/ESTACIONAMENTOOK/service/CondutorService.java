package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Condutor;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Marca;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.CondutorRepository;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.CondutorDTOS;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.MarcaDTOS;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CondutorService {

    private CondutorRepository condutorRepository;

    @Autowired
    public void CondutorRepository(CondutorRepository condutorRepository) {
        this.condutorRepository = condutorRepository;
    }
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Condutor condutor) {

    }

    public ResponseEntity<?> findById(Long id) {
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        return condutor == null
                ? ResponseEntity.badRequest().body("valor nao encontrado.")
                : ResponseEntity.ok(condutor);
    }
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.condutorRepository.findAll());
    }
    public ResponseEntity<?> findByAtivo(boolean b) {
        return ResponseEntity.ok(this.condutorRepository.findByAtivoTrue());

    }
    @Transactional
    public ResponseEntity<?> create(Condutor condutor) {

        if ( condutorRepository.findByCpf(condutor.getCpf()).isEmpty() ) {


            try {
                condutor.setAtivo(true);
                condutor.setTempoPago(LocalDateTime.from(LocalTime.of(0, 0)));
                condutor.setTempoDesconto(LocalDateTime.from(LocalTime.of(0,0 )));
                condutorRepository.save(condutor);
                TransactionAspectSupport.currentTransactionStatus().flush();
                return ResponseEntity.status(HttpStatus.CREATED).body(condutor);
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResponseEntity.badRequest().body(e.toString());
            }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF ja cadastrado");
        }
    }
        @Transactional
        public ResponseEntity<?> update(Long id, CondutorDTOS condutorDTOS) {
            Optional<Condutor> optionalCondutor = condutorRepository.findById(id);
            if (optionalCondutor.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor not found with ID: " + id);
            } else {
                Condutor condutor1 = optionalCondutor.get();
                BeanUtils.copyProperties(condutorDTOS, condutor1);
                condutor1.setAtualizacao(LocalDateTime.now());
                condutorRepository.save(condutor1);
                return ResponseEntity.ok().body("Condutor atualizado com sucesso");
            }
        }



    public ResponseEntity<?> delete(Long id) {
        final Condutor condutor = this.condutorRepository.findById(id).orElse(null);
        if (condutor == null) {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
        this.condutorRepository.delete(condutor);
        return ResponseEntity.ok("Registro Excluido");
    }


}
