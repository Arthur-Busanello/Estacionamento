package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Modelos",schema = "public")
public class Modelo extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome",nullable = false)
    private String nome;
    @Getter @Setter
    @JoinColumn(name = "marca",nullable = false)
    @ManyToOne
    private Marca marca;


    public Modelo() {
    }



    public Modelo(String nome, Marca marca){
        this.setNome(nome);
        this.setMarca(marca);
    }

}