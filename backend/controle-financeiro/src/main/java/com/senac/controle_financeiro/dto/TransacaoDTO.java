package com.senac.controle_financeiro.dto;

import com.senac.controle_financeiro.models.entities.Transacao;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TransacaoDTO {

    private Long id;
    private Double valor;
    private String estabelecimento;
    private String tipoDespesa;
    private Long usuario;

    public TransacaoDTO() {

    }

    public TransacaoDTO(Long id, Double valor, String estabelecimento, String tipoDespesa, Long usuario) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.tipoDespesa = tipoDespesa;
        this.usuario = usuario;
    }

    public TransacaoDTO(Transacao transacao){
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.tipoDespesa = tipoDespesa;
        this.usuario = usuario;
    }
}
