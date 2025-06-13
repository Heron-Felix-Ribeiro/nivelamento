package com.senac.controle_financeiro.application.object.empresa;

import com.senac.controle_financeiro.domain.entities.Empresa;

import java.time.LocalDate;

public record EmpresaResponse(Long id, String nome, String email, Double verba) {
    public static EmpresaResponse deEntidade(Empresa empresa) {
        return new EmpresaResponse(
                empresa.getId(),
                empresa.getNome(),
                empresa.getEmail().getEmail(),
                empresa.getVerba()
        );
    }
}
