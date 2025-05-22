package com.senac.controle_financeiro.domain.valueObjects;

import java.util.regex.Pattern;

public class Email {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    private final String email;

    public Email (String email) {
        if (!isValid(email)){
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Email () {
        this.email = "";
    }

    public boolean isValid(String email) {
        return email != null && PATTERN.matcher(email).matches();
    }
}
