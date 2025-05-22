package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.domain.valueObjects.CPF;
import com.senac.controle_financeiro.domain.valueObjects.Email;
import com.senac.controle_financeiro.domain.valueObjects.Senha;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity @Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    @Embedded
    private CPF cpf;
    @Embedded
    private Email email;
    private Double salario;
    private Integer idade;
    @Embedded
    private Senha senha;
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private Integer numero;

    public Usuario() {}

    public Usuario(UsuarioRequest entrada) {
        this.id = entrada.id();
        this.usuario = entrada.usuario();
        this.cpf = new CPF(entrada.cpf());
        this.email = new Email(entrada.email());
        this.salario = entrada.salario();
        this.idade = entrada.idade();
        this.senha = new Senha(entrada.senha());
        this.cep = entrada.cep();
        this.estado = entrada.estado();
        this.cidade = entrada.cidade();
        this.bairro = entrada.bairro();
        this.rua = entrada.rua();
        this.numero = entrada.numero();
    }


}
