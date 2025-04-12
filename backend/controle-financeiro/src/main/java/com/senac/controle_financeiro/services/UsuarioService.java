package com.senac.controle_financeiro.services;

import com.senac.controle_financeiro.models.entities.Usuario;
import com.senac.controle_financeiro.models.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario usuarioLogado () {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var usuarioLogado = usuarioRepository.findByUsuarioIgnoreCase(auth.getName()).orElse(null);
        return usuarioLogado;
    }

}
