package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "movimentacoes", schema = "public")
public class Movimentacao extends AbstractEntity {
    @Getter @Setter
    @JoinColumn(name = "veiculo", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Veiculo veiculo;
    @Getter @Setter
    @JoinColumn(name = "condutor", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Condutor condutor;
    @Getter @Setter
    @Column(name = "entrada_veiculo", nullable = false)
    private LocalDateTime entrada;
    @Getter @Setter
    @Column(name = "saida_veiculo")
    private LocalDateTime saida;
    @Getter @Setter
    @Column(name = "tempo")
    private LocalTime tempo;
    @Getter @Setter
    @Column(name = "tempo_Desconto")
    private LocalTime tempoDesconto;
    @Getter @Setter
    @Column(name = "tempo_multa")
    private LocalTime tempoMulta;
    @Getter @Setter
    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;
    @Getter @Setter
    @Column(name = "valor_multa")
    private BigDecimal valorMulta;
    @Getter @Setter
    @Column(name = "valot_total")
    private BigDecimal valorTotal;
    @Getter @Setter
    @Column(name = "valor_hora_multa")
    private BigDecimal valorHora;

    public Movimentacao() {



    }
}
