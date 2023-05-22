package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Condutor;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Marca;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.CondutorRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> save(Condutor condutor) {
        try {
            this.condutorRepository.save(condutor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
        }
    }
    public ResponseEntity<?> update(Long id, Condutor condutor) {
        if (id.equals(condutor.getId())) {
            this.condutorRepository.save(condutor);
            return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado !");
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
