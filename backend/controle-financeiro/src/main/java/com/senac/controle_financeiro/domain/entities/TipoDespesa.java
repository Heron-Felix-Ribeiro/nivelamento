package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.despesa.DespesaRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity @Table(name = "tipo_despesa")
public class TipoDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String despesa;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public TipoDespesa() {}

    public TipoDespesa(DespesaRequest entrada, Usuario usuario) {
        this.id = entrada.id();
        this.despesa = entrada.despesa();
        this.usuario = usuario;
    }


}
