package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;





import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Condutor;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Movimentacao;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Veiculo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.CondutorRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.MarcaRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.MovimentacaoRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.VeiculoRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.MovimentacaoDTOS;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    public void MovimentacaoRepository(MovimentacaoRepository marcaRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }
  @Autowired
  private VeiculoRepository veiculoRepository;
    @Autowired
    private MarcaRepository marcaRepository;
    @Autowired
    private CondutorRepository condutorRepository;

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


    @Transactional
    public ResponseEntity<?> create(MovimentacaoDTOS movimentacaoDTOS) {
        Condutor optionalCondutor = movimentacaoDTOS.getCondutor();
        Veiculo optionalVeiculo = movimentacaoDTOS.getVeiculo();
        if (optionalCondutor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Condutor not found ");
        }
        if (optionalVeiculo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found");
        }

        try {
            Movimentacao movimentacao1 = new Movimentacao();
//            Condutor condutor1 = optionalCondutor.get();
//            Veiculo veiculo1 = optionalVeiculo.get();
            BeanUtils.copyProperties(movimentacaoDTOS, movimentacao1);
            movimentacao1.setAtivo(true);
//            movimentacao1.setCondutor(condutor1);
//            movimentacao1.setVeiculo(veiculo1);
            movimentacao1.setAtualizacao(LocalDateTime.now());
            movimentacao1.setEntrada(LocalDateTime.now());
            movimentacaoRepository.save(movimentacao1);
            return ResponseEntity.ok().body("Movimentacao atualizado com sucesso");
        } catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());

        }
    }


//    public ResponseEntity<String> save(Movimentacao movimentacao) {
//        try {
//            this.movimentacaoRepository.save(movimentacao);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
//        }
//    }

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
