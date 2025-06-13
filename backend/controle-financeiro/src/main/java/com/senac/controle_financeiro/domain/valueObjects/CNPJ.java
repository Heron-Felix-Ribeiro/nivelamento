package com.senac.controle_financeiro.domain.valueObjects;

import jakarta.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
public class CNPJ {
    private String cnpj;

    private static final Pattern CNPJ_NUMERIC_PATTERN = Pattern.compile("^\\d{14}$");

    public CNPJ() {}

    public CNPJ(String cnpj) {
        if (cnpj == null) {
            throw new IllegalArgumentException("CNPJ não pode ser nulo.");
        }

        String cnpjNumeros = cnpj.replaceAll("[^0-9]", "");

        if (!CNPJ_NUMERIC_PATTERN.matcher(cnpjNumeros).matches() || cnpjNumeros.matches("(\\d)\\1{13}")) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        if (!validarDigitosVerificadores(cnpjNumeros)) {
            throw new IllegalArgumentException("CNPJ inválido.");
        }

        this.cnpj = cnpjNumeros;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    private static boolean validarDigitosVerificadores(String cnpj) {
        int[] pesosPrimeiroDigito = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] pesosSegundoDigito = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 12; i++) {
            soma += (cnpj.charAt(i) - '0') * pesosPrimeiroDigito[i];
        }
        int primeiroDigitoCalculado = soma % 11 < 2 ? 0 : 11 - (soma % 11);

        if (primeiroDigitoCalculado != (cnpj.charAt(12) - '0')) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 13; i++) {
            soma += (cnpj.charAt(i) - '0') * pesosSegundoDigito[i];
        }
        int segundoDigitoCalculado = soma % 11 < 2 ? 0 : 11 - (soma % 11);

        return segundoDigitoCalculado == (cnpj.charAt(13) - '0');
    }

    @Override
    public String toString() {
        return cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CNPJ cnpj1 = (CNPJ) o;
        return cnpj.equals(cnpj1.cnpj);
    }

    @Override
    public int hashCode() {
        return cnpj.hashCode();
    }
}