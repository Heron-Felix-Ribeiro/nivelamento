package com.senac.controle_financeiro.dto;

import com.senac.controle_financeiro.domain.entities.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO {

    private Long id;
    private String usuario;
    private String senha;
    private Double salario;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
        this.salario = usuario.getSalario();
    }
}
