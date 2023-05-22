package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Cor;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Modelo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Tipo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VeiculoRepository  extends JpaRepository<Veiculo, Long> {
    @Modifying
    List<Veiculo> findByPlaca(String placa);
    List<Veiculo> findByModelo(Modelo modelo);
    List<Veiculo> findByCor(Cor cor);
    List<Veiculo> findByTipo(Tipo tipo);



}
