package com.senac.controle_financeiro.application.object.despesa;

public record DespesaRequest(Long id,String despesa, String  usuario, String cnpj) {
}
