package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos;

import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Condutor;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity.Veiculo;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.CondutorRepository;
import br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Repository.VeiculoRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class MovimentacaoDTOS {
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private CondutorRepository condutorRepository;
    @NotBlank(message = "veiculo nao pode ser inexistente")
    private Veiculo veiculo;
    @NotBlank(message = "condutor nao pode ser inexistente")

    private Condutor condutor ;
    @NotBlank(message = "entrada nao pode ser inexistente")

    private LocalDateTime entrada;
    private LocalDateTime saida;
    private LocalTime tempo;
    private LocalTime tempoDesconto;
    private LocalTime tempoMulta;
    private BigDecimal valorDesconto;
    private BigDecimal valorMulta;
    private BigDecimal valorTotal;
    private BigDecimal valorHora;
    private BigDecimal valorHoraMulta;


    public MovimentacaoDTOS(CondutorRepository condutorRepository, VeiculoRepository veiculoRepository, Long veiculo, Long condutor, LocalDateTime entrada, LocalDateTime saida, LocalTime tempo, LocalTime tempoDesconto, LocalTime tempoMulta, BigDecimal valorDesconto, BigDecimal valorMulta, BigDecimal valorTotal, BigDecimal valorHora, BigDecimal valorHoraMulta) {
        this.veiculo = veiculoRepository.getReferenceById(veiculo);
        this.condutor = condutorRepository.getById(condutor);
        this.entrada = entrada;
        this.saida = saida;
        this.tempo = tempo;
        this.tempoDesconto = tempoDesconto;
        this.tempoMulta = tempoMulta;
        this.valorDesconto = valorDesconto;
        this.valorMulta = valorMulta;
        this.valorTotal = valorTotal;
        this.valorHora = valorHora;
        this.valorHoraMulta = valorHoraMulta;
    }


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Veiculo setVeiculo(Long veiculo) {
        this.veiculo = veiculoRepository.getById(veiculo);
        return (this.veiculo);
    }

    public Condutor getCondutor() {
        return this.condutor;
    }

    public void setCondutor(Long condutor) {
        this.condutor = condutorRepository.getById(condutor);
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void setSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public LocalTime getTempo() {
        return tempo;
    }

    public void setTempo(LocalTime tempo) {
        this.tempo = tempo;
    }

    public LocalTime getTempoDesconto() {
        return tempoDesconto;
    }

    public void setTempoDesconto(LocalTime tempoDesconto) {
        this.tempoDesconto = tempoDesconto;
    }

    public LocalTime getTempoMulta() {
        return tempoMulta;
    }

    public void setTempoMulta(LocalTime tempoMulta) {
        this.tempoMulta = tempoMulta;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigDecimal valorHora) {
        this.valorHora = valorHora;
    }

    public BigDecimal getValorHoraMulta() {
        return valorHoraMulta;
    }

    public void setValorHoraMulta(BigDecimal valorHoraMulta) {
        this.valorHoraMulta = valorHoraMulta;
    }
}
