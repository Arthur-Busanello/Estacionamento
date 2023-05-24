package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;


import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Marca;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Modelo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Veiculo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.ModeloRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.VeiculoRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.MarcaDTOS;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos.VeiculoDTOS;
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

public class VeiculoService {
    private VeiculoRepository veiculoRepository;

    @Autowired
    public void VeiculoRepository(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }
    @Autowired
private ModeloRepository modeloRepository;

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

    @Transactional
    public ResponseEntity<?> create(VeiculoDTOS veiculoDTOS) {
        Veiculo veiculo = new Veiculo();
        Modelo modelo = modeloRepository.getById(veiculoDTOS.getModelo());
        veiculo.setModelo(modelo);
        BeanUtils.copyProperties(veiculoDTOS, veiculo);
        try {
            veiculo.setAtivo(true);
            veiculoRepository.save(veiculo);
            TransactionAspectSupport.currentTransactionStatus().flush();
            return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResponseEntity.badRequest().body(e.getCause().getCause().getLocalizedMessage());
//        try {
//            this.veiculoRepository.save(veiculoDTOS);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
//        }
        }
    }
        @Transactional
        public ResponseEntity<?> update(Long id, VeiculoDTOS veiculoDTOS) {
            Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(id);
            if (optionalVeiculo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo not found with ID: " + id);
            } else {
                Veiculo veiculo1 = optionalVeiculo.get();
                BeanUtils.copyProperties(veiculoDTOS, veiculo1);
                veiculo1.setAtualizacao(LocalDateTime.now());
                veiculoRepository.save(veiculo1);
                return ResponseEntity.ok().body("Veiculo atualizado com sucesso");
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
