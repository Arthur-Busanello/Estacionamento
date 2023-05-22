package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "veiculos", schema = "public")
public class Veiculo extends AbstractEntity {
    @Getter @Setter
    @Column(name = "placa", nullable = false, unique = true)
    private String placa;
    @Getter @Setter
    @JoinColumn(name = "modelo", nullable = false)
    @ManyToOne
    private Modelo modelo;
    @Getter @Setter
    @Column(name = "cor", nullable = false)
    private Cor cor;
    @Getter @Setter
    @Column(name = "tipo", nullable = false)
    private Tipo tipo;
    @Getter @Setter
    @Column(name = "Ano", nullable = false)
    private Integer Ano;
}
