package com.senac.controle_financeiro.domain.valueObjects;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.regex.Pattern;

public class Senha {

    private static final String SENHA_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
    private static final Pattern PATTERN = Pattern.compile(SENHA_REGEX);

    private final String senha;

    public Senha() {
        this.senha = "";
    }

    public String getSenha() {
        return senha;
    }

    public Senha(String senha) {

        if (!isValid(senha)) {
            throw new IllegalArgumentException("Senha não cumpre as normas de criação");
        }

        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public static boolean verificarSenha(String senha, String  senhaCriptografada) {
        return BCrypt.checkpw(senha, senhaCriptografada);
    }

    public Boolean isValid(String senha) {
        return senha != null && PATTERN.matcher(senha).matches();
    }

}
