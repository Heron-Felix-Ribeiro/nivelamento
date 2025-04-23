package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.object.usuario.LoginResponse;
import com.senac.controle_financeiro.domain.repository.TokenRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import com.senac.controle_financeiro.application.object.usuario.LoginRequest;
import com.senac.controle_financeiro.application.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Controlador de Autenticação", description = "Endereço responsável por controlar a autenticação dos usuários")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Operation(summary = "Login do Usuário", description = "Login do usuário ")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

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

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha Inválida");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário Inválido");
    }

}
