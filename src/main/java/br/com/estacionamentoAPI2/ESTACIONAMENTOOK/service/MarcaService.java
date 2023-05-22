package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;




import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Marca;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.MarcaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<String> save(Marca marca) {
        try {
            this.marcaRepository.save(marca);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
        }
    }

    public ResponseEntity<?> update(Long id, Marca marca) {
        if (id.equals(marca.getId())) {
            this.marcaRepository.save(marca);
            return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado !");
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


}
