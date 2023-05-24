package br.com.estacionamentoAPI2.ESTACIONAMENTOOK.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Table(name = "abstractEntity",schema = "public")
public abstract class AbstractEntity {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false,unique = true)
    private long id;

    @Getter @Setter
    @Column(name = "cadastro")
    private LocalDateTime cadastro;
    @Getter @Setter
    @Column(name = "atualizacao")
    private LocalDateTime atualizacao;
    @Getter @Setter
    @Column(name = "ativo",nullable = false)
    private boolean ativo;
    @PrePersist
    private void prePersist() {
        this.cadastro = LocalDateTime.now();
        this.atualizacao = LocalDateTime.now();
    }
}
