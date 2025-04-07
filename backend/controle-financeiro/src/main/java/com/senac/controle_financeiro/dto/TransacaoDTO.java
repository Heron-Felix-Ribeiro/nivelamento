package com.senac.controle_financeiro.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TranscaoDTO {

    private Long id;
    private Double valor;
    private String estabelecimento;
    private String tipoDespesa;
    private Long usuario;

    public TranscaoDTO() {

    }

    public TranscaoDTO(Long id, Double valor, String estabelecimento, String tipoDespesa, Long usuario) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.tipoDespesa = tipoDespesa;
        this.usuario = usuario;
    }
}
