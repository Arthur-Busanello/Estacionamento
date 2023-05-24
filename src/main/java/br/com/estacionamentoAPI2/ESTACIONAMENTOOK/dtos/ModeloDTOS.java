package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class ModeloDTOS {
    @Getter
    @Setter
   @NotBlank(message = "Erro: nome nao pde ser nulo")
    @Size(max=50, message = "Erro: nome nao pode passar de 50 caracteres")
    private String nome;
    @Getter @Setter
    @NotNull(message = " Erro: o modelo deve possuir uma marca")
    private Long marca;


    public ModeloDTOS(String nome, Long marca) {
        this.nome = nome;
        this.marca = marca;
    }
}
