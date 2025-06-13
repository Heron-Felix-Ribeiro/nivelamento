package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.transacao.TransacaoRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    @JoinColumn(name = "responsavel_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn (name = "empresa_id")
    private Empresa empresa;
    private LocalDate dataCriacao;

    public Transacao () {}

    public Transacao(TransacaoRequest transacaoRequest, Usuario usuarioLogado, TipoDespesa tipoDespesa, Empresa empresa) {
        this.id = transacaoRequest.id();
        this.valor = transacaoRequest.valor();
        this.estabelecimento = transacaoRequest.estabelecimento();
        this.despesa = tipoDespesa;
        this.usuario = usuarioLogado;
        this.dataCriacao = LocalDate.now();
        this.empresa = empresa;
    }
}
