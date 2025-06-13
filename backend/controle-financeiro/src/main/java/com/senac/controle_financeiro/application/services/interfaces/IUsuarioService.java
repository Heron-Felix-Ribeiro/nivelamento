package com.senac.controle_financeiro.application.services.interfaces;

import com.senac.controle_financeiro.application.object.usuario.UsuarioResponse;
import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.domain.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    UsuarioResponse listarUm(Long id);

    Usuario salvar(UsuarioRequest entrada);

    List<UsuarioResponse> listarTodosAtivos();

    List<UsuarioResponse> listarTodosInativos();

    Integer contarUsuariosAtivos();

    UsuarioResponse usuarioEditado (UsuarioRequest entrada);

    Void mudarStatus (Long id);
}
