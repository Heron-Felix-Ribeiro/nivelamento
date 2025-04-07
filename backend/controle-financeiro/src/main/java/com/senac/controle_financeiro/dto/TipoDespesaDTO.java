package com.senac.controle_financeiro.dto;

import com.senac.controle_financeiro.models.entities.TipoDespesa;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TipoDespesaDTO {

    private Long id;
    private String nome;
    private Long usuario;

    public TipoDespesaDTO() {
    }

    public TipoDespesaDTO(String nome, Long usuario) {
        this.nome = nome;
        this.usuario = usuario;
    }


    public TipoDespesaDTO(TipoDespesa tipoDespesa) {
        this.id = tipoDespesa.getId();
        this.nome = tipoDespesa.getDespesa();
        this.usuario = tipoDespesa.getUsuario().getId();
    }

}
