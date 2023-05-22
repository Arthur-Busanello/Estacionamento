package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;


import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Veiculo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service

public class VeiculoService {
    private VeiculoRepository veiculoRepository;

    @Autowired
    public void VeiculoRepository(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Veiculo veiculo) {

    }
    public ResponseEntity<?> findById(Long id) {
        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
        return veiculo == null
                ? ResponseEntity.badRequest().body("valor nao encontrado.")
                : ResponseEntity.ok(veiculo);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.veiculoRepository.findAll());
    }

    public ResponseEntity<String> save(Veiculo veiculo) {
        try {
            this.veiculoRepository.save(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
        }
    }

    public ResponseEntity<?> update(Long id, Veiculo veiculo) {
        if (id.equals(veiculo.getId())) {
            this.veiculoRepository.save(veiculo);
            return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
    }

    public ResponseEntity<?> delete(Long id) {
        final Veiculo veiculo = this.veiculoRepository.findById(id).orElse(null);
        if (veiculo == null) {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
        this.veiculoRepository.delete(veiculo);
        return ResponseEntity.ok("Registro Excluido");
    }




}
