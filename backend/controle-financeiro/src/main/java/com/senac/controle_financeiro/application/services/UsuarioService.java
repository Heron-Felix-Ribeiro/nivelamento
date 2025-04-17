package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.application.services.interfaces.IUsuarioService;
import com.senac.controle_financeiro.domain.entities.Usuario;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import com.senac.controle_financeiro.application.object.usuario.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse usuarioLogado() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        var usuarioLogado = usuarioRepository.findByUsuarioIgnoreCase(auth.getName())
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o usuario"));

        return new UsuarioResponse(
                usuarioLogado.getUsuario(),
                usuarioLogado.getSalario(),
                usuarioLogado.getIdade());

    }

    @Override
    public Usuario salvar(UsuarioRequest entrada) {
        return usuarioRepository.save(new Usuario(entrada));
    }

    @Override
    public List<UsuarioResponse> listarTodos() {

        var usuarios = usuarioRepository.findAll();

        

        return null;
    }

}
