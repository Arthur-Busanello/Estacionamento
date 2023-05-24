package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@Entity
@Table(name="condutores", schema = "public")
@NoArgsConstructor
public class Condutor extends AbstractEntity {
    @Getter @Setter
    @Column(name = "nome",length = 20, unique = true)
    private String nome;
    @Getter @Setter
    @Column(name = "cpf", length = 20, unique = true, nullable = false)
    private String cpf;
    @Getter @Setter
    @Column(name = "telefone", length = 15, nullable = false)
    private String telefone;
    @Getter @Setter
    @Column(name = "tempo_pago", nullable = false)
    private LocalTime tempoPago;
    @Getter @Setter
    @Column(name = "tempo_desconto", nullable = false)
    private LocalTime tempoDesconto;
}

