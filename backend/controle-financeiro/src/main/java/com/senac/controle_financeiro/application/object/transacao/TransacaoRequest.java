package com.senac.controle_financeiro.application.object.transacao;

public record TransacaoRequest(Long id, Double valor, String estabelecimento, String despesa, Long usuario, String responsavel, String  cnpj
) {
}
