package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.usuario.LoginRequest;
import com.senac.controle_financeiro.application.object.usuario.LoginResponse;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> login (LoginRequest loginRequest) throws Exception {
        var usuarioSalvo = usuarioRepository.findByUsuarioIgnoreCase(loginRequest.usuario()).orElse(null);

        if (usuarioSalvo != null) {

            boolean logado = usuarioSalvo.getSenha().equals(loginRequest.senha());

            if (logado) {

                var retornoToken = tokenService.gerarToken(loginRequest);

                return ResponseEntity.ok().body(new LoginResponse(
                        usuarioSalvo.getId(),
                        retornoToken,
                        usuarioSalvo.getSalario()
                ));
            }

            throw new RuntimeException("Senha incorreta");
        }
        throw new RuntimeException("Usuário não encontrado");
    }
}
