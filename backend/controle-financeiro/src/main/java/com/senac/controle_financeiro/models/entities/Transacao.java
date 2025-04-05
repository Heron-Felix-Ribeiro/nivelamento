package com.senac.controle_financeiro.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity @Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double valor;
    @Column
    private String estabelecimento;
    @Column
    private String tipoDespesa;
    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;
}
