package com.senac.controle_financeiro.application.services;

import com.senac.controle_financeiro.application.object.usuario.LoginRequest;
import com.senac.controle_financeiro.application.object.usuario.LoginResponse;
import com.senac.controle_financeiro.application.services.interfaces.IAuthService;
import com.senac.controle_financeiro.domain.repository.EmpresaRepository;
import com.senac.controle_financeiro.domain.repository.UsuarioRepository;
import com.senac.controle_financeiro.domain.valueObjects.Email;
import com.senac.controle_financeiro.domain.valueObjects.Senha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {
        var usuarioSalvo = usuarioRepository.findByEmail(new Email(loginRequest.usuario())).orElse(null);

        if (usuarioSalvo != null) {
            boolean logado = Senha.verificarSenha(loginRequest.senha(), usuarioSalvo.getSenha().getSenha());
            if (logado) {
                var retornoToken = tokenService.gerarToken(loginRequest, usuarioSalvo);

                String cnpj = usuarioSalvo.getEmpresa() != null && usuarioSalvo.getEmpresa().getCnpj() != null
                        ? usuarioSalvo.getEmpresa().getCnpj().getCnpj() : "";

                Double verba = usuarioSalvo.getEmpresa() != null && usuarioSalvo.getEmpresa().getVerba() != null
                        ? usuarioSalvo.getEmpresa().getVerba() : 0;

                return ResponseEntity.ok().body(new LoginResponse(
                        cnpj,
                        retornoToken,
                        usuarioSalvo.getUsuario(),
                        verba
                ));
            }
            throw new RuntimeException("Senha incorreta");
        }
        throw new RuntimeException("Usuário não encontrado");
    }

    @Override
    public Boolean adminLogado(String subject) {
        var usuarioLogado = usuarioRepository.findByEmail(new Email(subject))
                .orElseThrow(() -> new RuntimeException("Erro ao encontrar o usuario"));

        return usuarioLogado.getAdmin();
    }
}


