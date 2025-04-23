package com.senac.controle_financeiro.application.object.transacao;

public record TransacaoResponse(Long id,Double valor, String estabelecimento, String despesa, Long usuario) {
}
