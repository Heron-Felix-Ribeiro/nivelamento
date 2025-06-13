package com.senac.controle_financeiro.application.object.empresa;

import java.time.LocalDate;

public record EmpresaRequest(
        String nome,
        LocalDate dataCriacao,
        String cnpj,
        String email,
        Double verba,
        String ibge,
        String bairro,
        String rua,
        Integer numero,
        String cep
) {
}
