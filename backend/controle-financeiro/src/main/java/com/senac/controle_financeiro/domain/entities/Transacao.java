package com.senac.controle_financeiro.domain.entities;

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
    @ManyToOne
    @JoinColumn
    private TipoDespesa despesa;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
