package com.senac.controle_financeiro.application.object.usuario;

import jakarta.persistence.Column;

public record UsuarioRequest(
        Long id,
        String usuario,
        Double salario,
        Integer idade,
        String senha,
        String cep,
        String estado,
        String cidade,
        String bairro,
        String rua,
        Integer numero) {
}
