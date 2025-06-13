package com.senac.controle_financeiro.presentation.controllers;

import com.senac.controle_financeiro.application.services.AuthService;
import com.senac.controle_financeiro.application.object.usuario.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Controlador de Autenticação do Usuário", description = "Endereço responsável por controlar a autenticação dos usuários")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    @Operation(summary = "Login do Usuário", description = "Recebe os dados de entrada e valida se o usuário está autenticado no sistema")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            var resposta = authService.login(loginRequest);
            return resposta;
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
