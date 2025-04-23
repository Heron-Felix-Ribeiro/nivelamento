package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
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

    public Transacao() {}

    public Transacao(TransacaoRequest transacaoRequest, Usuario usuarioLogado, TipoDespesa tipoDespesa) {
        this.id = transacaoRequest.id();
        this.valor = transacaoRequest.valor();
        this.estabelecimento = transacaoRequest.estabelecimento();
        this.despesa = tipoDespesa;
        this.usuario = usuarioLogado;
    }
}
