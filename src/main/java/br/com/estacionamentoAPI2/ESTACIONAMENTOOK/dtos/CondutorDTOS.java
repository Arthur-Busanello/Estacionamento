package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalTime;

@Data
public class CondutorDTOS  {
    @Size(max = 60,message = "erro o nome nao deve possuir mais de 50 caracteres.")
    @NotBlank(message = "erro nome em branco")
    private String nome;

    @Size(max = 14,message = "erro o cpf deve possuir 14 caracteres contando com (.&-)")
    @NotBlank(message = "erro nome em branco")
    private String cpf;

    @Size(max = 12,message = "erro o cpf deve possuir 12 caracteres contando com ddd")
    @NotBlank(message = "telefone em branco")
    private String telefone;


    public String getNome() {
        return nome;
    }

    public CondutorDTOS(String nome, String cpf, String telefone, LocalTime tempoPago, LocalTime tempoDesconto) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;


    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
