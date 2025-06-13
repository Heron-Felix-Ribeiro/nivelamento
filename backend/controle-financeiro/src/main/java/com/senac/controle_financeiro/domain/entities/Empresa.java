package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.empresa.EmpresaRequest;
import com.senac.controle_financeiro.domain.valueObjects.CNPJ;
import com.senac.controle_financeiro.domain.valueObjects.Email;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity @Setter @Getter @Table(name = "empresa")
public class Empresa {

    @Id @GeneratedValue
    private Long id;
    private String nome;
    @Embedded @Column(unique = true)
    private CNPJ cnpj;
    private Double verba;
    @Embedded
    private Email email;
    private LocalDate dataCricao;
    private boolean estaAtivo;

    public Empresa() {}

    public Empresa(EmpresaRequest entrada) {
        this.nome = entrada.nome();
        this.cnpj = new CNPJ(entrada.cnpj());
        this.email = new Email(entrada.email());
        this.dataCricao = entrada.dataCriacao();
        this.verba = entrada.verba();
        this.estaAtivo = true;
    }

}
