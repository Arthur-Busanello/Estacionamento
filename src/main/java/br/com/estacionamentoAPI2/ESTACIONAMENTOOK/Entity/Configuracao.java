package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "configuracoes", schema = "public")
public class Configuracao extends AbstractEntity {
    @Column(name = "valor_hora",nullable = false)
    @Getter
    @Setter
    private BigDecimal ValorHora;
    @Column(name = "valor_multa",nullable = false)
    @Getter @Setter
    private BigDecimal ValorMulta;
    @Column(name = "inicio_expediente",nullable = false)
    @Getter @Setter
    private LocalDateTime InicioExpediente;
    @Column(name = "tempo_parado_desconto",nullable = false)
    @Getter @Setter
    private LocalDateTime TempoParadoDesconto;
    @Column(name = "tempo_de_desconto",nullable = false)
    @Getter @Setter
    private LocalDateTime TempoDeDesconto;
    @Column(name = "gerar_desconto",nullable = false)
    @Getter @Setter
    private Boolean GerarDesconto;
    @Column(name = "vagas_moto",nullable = false)
    @Getter @Setter
    private Integer VagasMoto;
    @Column(name = "vagas_carro",nullable = false)
    @Getter @Setter
    private Integer VagasCarro;
    @Column(name = "vagas_vam",nullable = false)
    @Getter @Setter
    private Integer VagasVam;
}

