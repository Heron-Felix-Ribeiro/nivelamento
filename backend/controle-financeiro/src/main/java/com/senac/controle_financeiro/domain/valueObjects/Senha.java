package com.senac.controle_financeiro.domain.valueObjects;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Senha {

    private final String senha;

    public Senha() {
        this.senha = "";
    }

    public Senha(String senha) {

        if (isValid(senha)) {
            throw new IllegalArgumentException("Senha não cumpre as normas de criação");
        }

        String senhaCriptografada = BCrypt.hashpw(senha, BCrypt.gensalt());

        this.senha = senhaCriptografada;

    }

    public Boolean isValid(String senha) {
        // Mudar futuramente para um minimo de oito caractéres
        if (senha.length() > 2) {
            return true;
        }

        return false;
    }

}
