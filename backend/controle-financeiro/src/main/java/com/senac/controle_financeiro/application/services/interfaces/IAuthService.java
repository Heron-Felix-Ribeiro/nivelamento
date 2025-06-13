package com.senac.controle_financeiro.application.services.interfaces;

import com.senac.controle_financeiro.application.object.usuario.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

    ResponseEntity<?> login(LoginRequest loginRequest) throws Exception;

    Boolean adminLogado(String subject);
}
