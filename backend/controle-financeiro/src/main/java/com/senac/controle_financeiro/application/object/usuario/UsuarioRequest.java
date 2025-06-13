package com.senac.controle_financeiro.application.object.usuario;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record UsuarioRequest(
        Long id,
        String usuario,
        String cpf,
        String email,
        Double salario,
        LocalDate idade,
        String senha,
        String cnpj,
        String cep,
        String estado,
        String cidade,
        String bairro,
        String rua,
        Integer numero
) {
}
