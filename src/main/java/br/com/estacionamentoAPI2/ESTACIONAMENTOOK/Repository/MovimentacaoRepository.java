package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByCondutorId(Long condutorId);
    List<Movimentacao> findByVeiculoId(Long condutorId);

}
