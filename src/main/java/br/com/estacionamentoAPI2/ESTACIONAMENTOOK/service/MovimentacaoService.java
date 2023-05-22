package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;





import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Movimentacao;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class MovimentacaoService {
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    public void MovimentacaoRepository(MovimentacaoRepository marcaRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(final Movimentacao movimentacao) {

    }
    public ResponseEntity<?> findById(Long id) {
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        return movimentacao == null
                ? ResponseEntity.badRequest().body("valor nao encontrado.")
                : ResponseEntity.ok(movimentacao);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.movimentacaoRepository.findAll());
    }

    public ResponseEntity<String> save(Movimentacao movimentacao) {
        try {
            this.movimentacaoRepository.save(movimentacao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
        }
    }

    public ResponseEntity<?> update(Long id, Movimentacao movimentacao) {
        if (id.equals(movimentacao.getId())) {
            this.movimentacaoRepository.save(movimentacao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
    }

    public ResponseEntity<?> delete(Long id) {
        final Movimentacao movimentacao = this.movimentacaoRepository.findById(id).orElse(null);
        if (movimentacao == null) {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
        this.movimentacaoRepository.delete(movimentacao);
        return ResponseEntity.ok("Registro Excluido");
    }




}
