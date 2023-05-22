package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.service;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Configuracao;


import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ConfiguracaoService {
    private ConfiguracaoRepository configuracaoRepository;

    @Autowired
    public void ConfiguracaoRepository(ConfiguracaoRepository configuracaoRepository) {
        this.configuracaoRepository = configuracaoRepository;
    }

        @Transactional(rollbackFor = Exception.class)
        public void cadastrar(final Configuracao configuracao) {

        }
    public ResponseEntity<?> findById(Long id) {
        final Configuracao configuracao = this.configuracaoRepository.findById(id).orElse(null);
        return configuracao == null
                ? ResponseEntity.badRequest().body("valor nao encontrado.")
                : ResponseEntity.ok(configuracao);
    }

    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.configuracaoRepository.findAll());
    }

    public ResponseEntity<String> save(Configuracao configuracao) {
        try {
            this.configuracaoRepository.save(configuracao);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar .");
        }
    }

    public ResponseEntity<?> update(Long id, Configuracao configuracao) {
        if (id.equals(configuracao.getId())) {
            this.configuracaoRepository.save(configuracao);
            return ResponseEntity.ok().body("Registro atualizado com sucesso !!!");
        } else {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
    }

    public ResponseEntity<?> delete(Long id) {
        final Configuracao configuracao = this.configuracaoRepository.findById(id).orElse(null);
        if (configuracao == null) {
            return ResponseEntity.badRequest().body("ID não encontrado !");
        }
        this.configuracaoRepository.delete(configuracao);
        return ResponseEntity.ok("Registro Excluido");
    }




}
