package com.senac.controle_financeiro.controllers;

import com.senac.controle_financeiro.models.repository.TokenRepository;
import com.senac.controle_financeiro.models.repository.UsuarioRepository;
import com.senac.controle_financeiro.object.LoginRequest;
import com.senac.controle_financeiro.services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {

        var usuario = usuarioRepository.findByUsuarioIgnoreCase(loginRequest.usuario());

        if (!usuario.isEmpty()) {

            var retornoToken = tokenService.gerarToken(loginRequest, usuario);

            return ResponseEntity.ok().body(retornoToken);
        }

        throw new Exception("Não foi encontrado o usuário");

    }

}
