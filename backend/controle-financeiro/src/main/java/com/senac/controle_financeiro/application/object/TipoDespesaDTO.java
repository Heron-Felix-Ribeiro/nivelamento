package com.senac.controle_financeiro.application.object;

import com.senac.controle_financeiro.domain.entities.TipoDespesa;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TipoDespesaDTO {

    private Long id;
    private String despesa;
    private Long usuario;

    public TipoDespesaDTO() {
    }

    public TipoDespesaDTO(String nome, Long usuario) {
        this.despesa = nome;
        this.usuario = usuario;
    }


    public TipoDespesaDTO(TipoDespesa tipoDespesa) {
        this.id = tipoDespesa.getId();
        this.despesa = tipoDespesa.getDespesa();
        this.usuario = tipoDespesa.getUsuario().getId();
    }

}
