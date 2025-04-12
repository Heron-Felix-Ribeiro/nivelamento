package com.senac.controle_financeiro.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.senac.controle_financeiro.models.entities.Token;
import com.senac.controle_financeiro.models.entities.Usuario;
import com.senac.controle_financeiro.models.repository.TokenRepository;
import com.senac.controle_financeiro.object.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
public class TokenService {

    @Value("${spring.expiration_time}")
    private Long expirationTime;

    @Value("${spring.secretkey}")
    private String secret;

    @Value("${spring.emissor}")
    private String emissor;

    @Autowired
    private TokenRepository tokenRepository;

    public String gerarToken (LoginRequest loginRequest) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer(emissor)
                    .withSubject(loginRequest.usuario())
                    .withExpiresAt(this.gerarDataExpiracao())
                    .sign(algorithm);

            return token;

        } catch (Exception e){
            return null;
        }

    }

    public DecodedJWT validarToken (String token) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(emissor).build();

        return verifier.verify(token);

    }

    public Instant gerarDataExpiracao () {

        return LocalDateTime.now()
                .plusMinutes(expirationTime)
                .toInstant(ZoneOffset.of("-03:00"));

    }
}
