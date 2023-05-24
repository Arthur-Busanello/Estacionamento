package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MarcaDTOS {

    @Getter
    @Setter
    @NotBlank(message = "Erro: nome da marca nao pode estar vazio")
    @Size(max = 50, message = "Erro: nome da marca nao pode ser maior que 50 caracteres")
    private String nome;
}
