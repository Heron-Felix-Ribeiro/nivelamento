package com.senac.controle_financeiro.dto;

import com.senac.controle_financeiro.domain.entities.Transacao;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TransacaoDTO {

    private Long id;
    private Double valor;
    private String estabelecimento;
    private String despesa;
    private Long usuario;

    public TransacaoDTO() {

    }

    public TransacaoDTO(Long id, Double valor, String estabelecimento, String tipoDespesa, Long usuario) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.despesa = tipoDespesa;
        this.usuario = usuario;
    }

    public TransacaoDTO(Transacao transacao){
        this.id = transacao.getId();
        this.valor = transacao.getValor();
        this.estabelecimento = transacao.getEstabelecimento();
        this.despesa = transacao.getDespesa().getDespesa();
        this.usuario = transacao.getUsuario().getId();
    }
}
