package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.despesa.DespesaRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @Entity @Table(name = "tipo_despesa")
public class TipoDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String despesa;
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario usuario;
    private LocalDate dataCriacao;

    public TipoDespesa () {}

    public TipoDespesa(DespesaRequest entrada, Usuario usuario, Empresa empresa) {
        this.id = entrada.id();
        this.despesa = entrada.despesa();
        this.empresa = empresa;
        this.usuario = usuario;
        this.dataCriacao = LocalDate.now();
    }


}
