package com.senac.controle_financeiro.presentation.configuration;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.senac.controle_financeiro.application.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.equals("/auth")
                || path.equals("/logout")
                || path.equals("/usuario")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")
                || path.startsWith("/webjars")
                || path.startsWith("/swagger-resources")){

            filterChain.doFilter(request, response);
            return;

        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.replace("Bearer ", "");

            try {

                DecodedJWT jwt = tokenService.validarToken(token);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        jwt.getSubject(),
                        null, Collections.emptyList()
                );

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception e) {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token Invalido");
                return;

            }
        }
        filterChain.doFilter(request, response);
    }

}
