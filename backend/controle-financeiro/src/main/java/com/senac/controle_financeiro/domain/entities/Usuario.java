package com.senac.controle_financeiro.domain.entities;

import com.senac.controle_financeiro.application.object.usuario.UsuarioRequest;
import com.senac.controle_financeiro.domain.valueObjects.CPF;
import com.senac.controle_financeiro.domain.valueObjects.Email;
import com.senac.controle_financeiro.domain.valueObjects.Senha;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @Entity @Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    @Embedded
    @Column (unique = true)
    private CPF cpf;
    @Embedded
    @Column (unique = true)
    private Email email;
    private LocalDate dataNascimento;
    @Embedded
    private Senha senha;
    private Boolean estaAtivo;
    @ManyToOne
    @JoinColumn (name = "empresa_id")
    private Empresa empresa;
    private Boolean admin;

    public Usuario() {}

    public Usuario(UsuarioRequest entrada, Empresa empresa) {
        this.id = entrada.id();
        this.usuario = entrada.usuario();
        this.cpf = new CPF(entrada.cpf());
        this.email = new Email(entrada.email());
        this.dataNascimento = entrada.idade();
        this.senha = new Senha(entrada.senha());
        this.estaAtivo = true;
        this.empresa = empresa;
    }


}
