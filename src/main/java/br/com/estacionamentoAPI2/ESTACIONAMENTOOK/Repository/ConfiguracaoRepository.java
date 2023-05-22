package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Configuracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {


}
