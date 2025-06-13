package com.senac.controle_financeiro.application.object.usuario;

public record LoginResponse(String cnpj, String token, String usuario, Double verba) {
}
