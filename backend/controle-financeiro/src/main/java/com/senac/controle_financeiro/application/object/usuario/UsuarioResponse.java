package com.senac.controle_financeiro.application.object.usuario;

import java.time.LocalDate;

public record UsuarioResponse(Long id,String usuario, String empresa, String email) {
}
