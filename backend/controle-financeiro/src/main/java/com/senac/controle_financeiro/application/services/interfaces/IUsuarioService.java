package com.senac.controle_financeiro.application.services.interfaces;

import com.senac.controle_financeiro.application.object.usuario.UsuarioResponse;
import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.domain.entities.Usuario;

import java.util.List;

public interface IUsuarioService {

    UsuarioResponse usuarioLogado ();

    Usuario salvar(UsuarioRequest entrada);

    List<UsuarioResponse> listarTodos ();

    UsuarioResponse usuarioEditado (UsuarioRequest entrada);

    Long deletar (Long id);
}
