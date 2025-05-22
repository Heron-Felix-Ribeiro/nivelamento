package com.senac.controle_financeiro.domain.valueObjects;

import java.util.regex.Pattern;

public class CEP {

    private static final String CEP_REGEX = "^\\d{5}-\\d{3}$";
    private static final Pattern PATTERN = Pattern.compile(CEP_REGEX);

    private final String cep;

    public CEP(String cep) {
        if (!isValid(cep)) {
            throw new IllegalArgumentException("CEP inválido");
        }
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public static boolean isValid(String cep) {
        return cep != null && PATTERN.matcher(cep).matches();
    }
}
