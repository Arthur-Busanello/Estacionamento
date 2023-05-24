package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CondutorRepository extends JpaRepository <Condutor, Long> {
    public List<Condutor> findByNome (final String nome);
    @Query("from Condutor where nome like :nome")
    public List<Condutor> findByNomeLike (@Param("nome") final String nome);
    @Query (value = "select * from Condutor where nome like :nome", nativeQuery = true)
    public List<Condutor> findByNomeLikeNative (@Param("nome") final String nome);

    @Query("SELECT c FROM Condutor c WHERE c.cpf = :cpf")
    List<Condutor> findByCpf(@Param("cpf") String cpf);
    List<Condutor> findByAtivoTrue();
}
