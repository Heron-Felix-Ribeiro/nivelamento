package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.domain.valueObjects.CPF;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity @Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String usuario;
    @Embedded
    private CPF cpf;
    @Column
    private Double salario;
    @Column
    private Integer idade;
    @Column
    private String senha;
    @Column
    private String cep;
    @Column
    private String estado;
    @Column
    private String cidade;
    @Column
    private String bairro;
    @Column
    private String rua;
    @Column
    private Integer numero;

    public Usuario() {}

    public Usuario(UsuarioRequest entrada) {

        this.id = entrada.id();
        this.usuario = entrada.usuario();
        this.cpf = new CPF(entrada.cpf());
        this.salario = entrada.salario();
        this.idade = entrada.idade();
        this.senha = entrada.senha();
        this.cep = entrada.cep();
        this.estado = entrada.estado();
        this.cidade = entrada.cidade();
        this.bairro = entrada.bairro();
        this.rua = entrada.rua();
        this.numero = entrada.numero();

    }


}
